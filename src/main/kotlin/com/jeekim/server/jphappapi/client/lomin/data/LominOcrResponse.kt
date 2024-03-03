package com.jeekim.server.jphappapi.client.lomin.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.model.prescription.Bbox
import com.jeekim.server.jphappapi.model.prescription.Prediction
import com.jeekim.server.jphappapi.model.prescription.PrescriptionCode
import com.jeekim.server.jphappapi.model.prescription.PrescriptionContent
import com.jeekim.server.jphappapi.model.prescription.SelfPayRateCode
import com.jeekim.server.jphappapi.model.prescription.SelfPayRateCodes
import com.jeekim.server.jphappapi.model.prescription.SingleItem
import com.jeekim.server.jphappapi.utils.Constants
import com.jeekim.server.jphappapi.utils.clearBrackets
import com.jeekim.server.jphappapi.utils.convertTo
import com.jeekim.server.jphappapi.utils.deletePattern
import com.jeekim.server.jphappapi.utils.getString
import java.util.regex.Matcher
import java.util.regex.Pattern

data class LominOcrResponse (
    @JsonProperty("prediction")
    val prediction: Prediction
){
    fun toOcrResponse(): OcrResponse {
        val ocrResponse = OcrResponse()
        prediction.keyValueList
            .forEach { keyValue -> insertGeneralContents(ocrResponse, keyValue) }
        prediction.tableList
            .forEach { table -> insertPrescriptionContents(ocrResponse, table) }
        addExtraPersonalInfoBboxes(ocrResponse)
        return ocrResponse
    }

    private fun addExtraPersonalInfoBboxes(ocrResponse: OcrResponse) {
        for (keyValue in prediction.keyValueList) {
            if (keyValue.key == PrescriptionCode.PRESCRIPTION_REF.code) {
                val bbox: Bbox = keyValue.bbox.convertTo(Bbox::class.java)
                if (bbox.height != 0 && bbox.width == 0) {
                    ocrResponse.extraPersonalInfoBboxList.add(bbox)
                }
            }
        }
        val patientName: String = ocrResponse.patientName.value ?: return
        for (ocrText in prediction.ocrTextList) {
            if (ocrText.isNotMapped() && ocrText.text.contains(patientName)) {
                val bbox: Bbox = ocrText.bbox.convertTo(Bbox::class.java)
                ocrResponse.extraPersonalInfoBboxList.add(bbox)
            }
        }
    }

    private fun insertGeneralContents(ocrResponse: OcrResponse, keyValue: Prediction.KeyValue) {
        val key: String = keyValue.key
        val value: Any = keyValue.value
        val needCheck: Boolean = determineNeedCheck(key, value, keyValue.confidence)
        when (PrescriptionCode.of(key)) {
            PrescriptionCode.PATIENT_CATEGORY -> ocrResponse.patientCategory.initialize(value, needCheck)
            PrescriptionCode.ISSUANCE_DATE -> ocrResponse.issuanceDate.initialize(value, needCheck)
            PrescriptionCode.ISSUANCE_NUMBER -> ocrResponse.issuanceNumber.initialize(value, needCheck)
            PrescriptionCode.NURSING_INST_NUMBER -> ocrResponse.nursingInstNumber.initialize(value, needCheck)
            PrescriptionCode.DOCTOR_NAME -> { ocrResponse.doctorName.initialize(value, needCheck, keyValue.bbox.convertTo(Bbox::class.java)) }
            PrescriptionCode.PATIENT_NAME -> { ocrResponse.patientName.initialize(value, needCheck, keyValue.bbox.convertTo(Bbox::class.java)) }
            PrescriptionCode.PATIENT_RRN -> { ocrResponse.patientRrn.initialize(value, needCheck, keyValue.bbox.convertTo(Bbox::class.java)) }
            PrescriptionCode.MEDICAL_INST_NAME -> ocrResponse.medicalInstName.initialize(value, needCheck)
            PrescriptionCode.LICENSE_NUMBER -> { ocrResponse.licenseNumber.initialize(value, needCheck, keyValue.bbox.convertTo(Bbox::class.java)) }
            PrescriptionCode.DISEASE_CODES -> ocrResponse.diseaseCodes.initialize(value, needCheck)
            PrescriptionCode.PRESCRIPTION_REF -> ocrResponse.prescriptionRef.initialize(value, needCheck)
            PrescriptionCode.SELF_PAY_CODE -> ocrResponse.selfPayCode.initialize(value, needCheck)
            else -> {}
        }
    }
    private fun insertPrescriptionContents(ocrResponse: OcrResponse, table: Prediction.Table) {
        val prescriptionContents: MutableList<PrescriptionContent> = ArrayList<PrescriptionContent>()
        val keys: List<String> = table.columnHeader.keys
        val contents: List<List<String>> = table.body.content
        val confidences: List<List<Double>> = table.body.scores
        for (rowIndex in contents.indices) { //처방항목 개수
            prescriptionContents.add(fillInPrescriptionContents(rowIndex, keys, contents, confidences))
        }
        setOcrResponsePrescriptionContents(ocrResponse, prescriptionContents, table)
    }
    private fun setOcrResponsePrescriptionContents(
        ocrResponse: OcrResponse,
        prescriptionContents: List<PrescriptionContent>,
        table: Prediction.Table
    ) {
        if (table.isInternalTable()) {
            ocrResponse.internalPrescriptionContents = prescriptionContents
        } else {
            ocrResponse.injectionPrescriptionContents = prescriptionContents
        }
    }

    private fun fillInPrescriptionContents(
        rowIndex: Int,
        keys: List<String>,
        contents: List<List<String>>,
        confidences: List<List<Double>>
    ): PrescriptionContent {
        val prescriptionContent = PrescriptionContent()
        for (keyIndex in keys.indices) { //5개
            val key = keys[keyIndex]
            val value = contents[rowIndex][keyIndex]
            val needCheck = confidences[rowIndex][keyIndex] < Constants.NEED_CHECK_CONFIDENCE_THRESHOLD
            when (PrescriptionCode.of(key)) {
                PrescriptionCode.INTERNAL_DRUG_NAME, PrescriptionCode.INJECTION_DRUG_NAME -> prescriptionContent.drugName.initialize(value, needCheck)
                PrescriptionCode.INTERNAL_ONE_DOSE, PrescriptionCode.INJECTION_ONE_DOSE -> prescriptionContent.oneDose.initialize(value, needCheck)
                PrescriptionCode.INTERNAL_DOSING_PER_DAY, PrescriptionCode.INJECTION_DOSING_PER_DAY -> prescriptionContent.dosingPerDay.initialize(value, needCheck)
                PrescriptionCode.INTERNAL_TOTAL_DOSING_DAYS, PrescriptionCode.INJECTION_TOTAL_DOSING_DAYS -> prescriptionContent.totalDosingDays.initialize(value, needCheck)
                PrescriptionCode.INTERNAL_SELF_PAY_RATE_CODE, PrescriptionCode.INJECTION_SELF_PAY_RATE_CODE -> prescriptionContent.selfPayRateCode.initialize(value, needCheck)
                else -> {}
            }
        }
        setDrugCode(prescriptionContent)
        checkAndSetSelfPayRateCode(prescriptionContent)
        return prescriptionContent
    }

    private fun checkAndSetSelfPayRateCode(prescriptionContent: PrescriptionContent) {
        val selfPayRateCode: SelfPayRateCode = prescriptionContent.selfPayRateCode
        val selfPayRateCodeString = selfPayRateCode.value
        val drugNameObject = prescriptionContent.drugName
        var drugName = drugNameObject.value
        val drugNameNeedCheck = drugNameObject.needCheck
        val defaultSelfPayRateCode = SelfPayRateCode()
        defaultSelfPayRateCode.initialize(SelfPayRateCodes.REIMBURSEMENT.word, drugNameNeedCheck)
        if (selfPayRateCodeString != null && drugName == null) {
            return
        }

        //로민 자기부담률구분기호 인식 X / 약품이름 X
        if (selfPayRateCodeString == null && drugName == null) {
            prescriptionContent.selfPayRateCode = defaultSelfPayRateCode
            return
        }
        if (selfPayRateCodeString == null) {
            drugName = setSelfPayRateCodeAndGetResultDrugName(checkNotNull(drugName) , defaultSelfPayRateCode, true)
            prescriptionContent.selfPayRateCode = defaultSelfPayRateCode
        } else {
            drugName = setSelfPayRateCodeAndGetResultDrugName(checkNotNull(drugName), defaultSelfPayRateCode, false)
        }
        drugNameObject.value = drugName
        prescriptionContent.drugName = drugNameObject
    }

    private fun setSelfPayRateCodeAndGetResultDrugName(
        drugName: String,
        selfPayRateCode: SelfPayRateCode,
        setDrugCode: Boolean
    ): String {
        var longestSoFar = ""
        var resultDrugName = drugName
        for (key in Constants.SELF_PAY_RATE_KEYWORDS.keys) {
            val possible = Constants.SELF_PAY_RATE_KEYWORDS[key]!!.stream()
                .filter { s: String? -> drugName.contains(s!!) }
                .max(Comparator.comparing { obj: String? -> obj!!.length })
                .orElse(null)
            if (possible != null && possible.length > longestSoFar.length) {
                longestSoFar = possible
                if (setDrugCode) {
                    selfPayRateCode.value = key
                }
                resultDrugName = drugName.deletePattern(possible)
            }
        }
        return resultDrugName
    }
    private fun setDrugCode(prescriptionContent: PrescriptionContent) {
        val drugNameObject = prescriptionContent.drugName
        val drugName = drugNameObject.value
        val needCheck = drugNameObject.needCheck
        if (drugName != null) {
            val drugCode = SingleItem(null, needCheck)
            val drugCodeWithBrackets = checkDrugCodePattern(drugName)
            if (drugCodeWithBrackets != null) {
                drugCode.value = drugCodeWithBrackets.clearBrackets()
                drugNameObject.value = drugName.deletePattern(drugCodeWithBrackets)
            }
            prescriptionContent.drugName = drugNameObject
            prescriptionContent.drugCode = drugCode
        }
    }
    private fun checkDrugCodePattern(drugNameString: String): String? {
        var matcher: Matcher = DRUG_CODE_PATTERN.matcher(drugNameString)
        if (matcher.find()) {
            return drugNameString.substring(matcher.start(), matcher.end()).trim { it <= ' ' }
        }
        matcher = DRUG_CODE_PATTERN_A.matcher(drugNameString)
        return if (matcher.find()) {
            drugNameString.substring(matcher.start(), matcher.end()).trim { it <= ' ' }
        } else null
    }

    private fun determineNeedCheck(key: String, value: Any, confidence: Any): Boolean {
        val confidenceValue: Double = calculateConfidenceValue(confidence)
        if (PrescriptionCode.of(key).needCheckNotEssential) {
            if (value.getString() == null && confidenceValue == 0.0) {
                return false
            }
        }
        return confidenceValue < Constants.NEED_CHECK_CONFIDENCE_THRESHOLD
    }
    private fun calculateConfidenceValue(confidence: Any): Double {
        if (confidence is List<*>) {
            val confidenceList = confidence as List<Double>
            return confidenceList.average()
        }
        return confidence as Double
    }

    companion object{
        private val DRUG_CODE_PATTERN = Pattern.compile("[{\\[\\(]?[0-9]{9}[}\\]\\)]?")
        private val DRUG_CODE_PATTERN_A = Pattern.compile("[{\\[\\(]?A[0-9]{8}[}\\]\\)]?")

    }

}