package com.jeekim.server.jphappapi.client.lomin.model

import com.jeekim.server.jphappapi.utils.ParserUtils
import com.jeekim.server.jphappapi.model.prescription.PatientCategoryEnum
import com.jeekim.server.jphappapi.model.prescription.PrescriptionRefTypeEnum
import com.jeekim.server.jphappapi.model.prescription.SelfPayRateCodeEnum
import com.jeekim.server.jphappapi.utils.CalendarUtils

data class PatientCategory(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem {
    override fun initialize(value: Any?, needCheck: Boolean) {
        val valueString = ParserUtils.getStringValueWithoutWhiteSpace(value)
        val patientCategoryEnum = PatientCategoryEnum.of(valueString)

        this.value = patientCategoryEnum.word
        this.needCheck = needCheck
    }
}

data class IssuanceDate(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem {
    override fun initialize(value: Any?, needCheck: Boolean) {
        val generatedDate = CalendarUtils.checkDateFormatAndGet(ParserUtils.getStringValue(value))
        this.value = generatedDate.date
        this.needCheck = generatedDate.generated || needCheck
    }
}

data class GeneratedDate(
    val date: String,
    val generated: Boolean
)

data class IssuanceNumber(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem {
    override fun initialize(value: Any?, needCheck: Boolean) {
        val valueString = ParserUtils.getStringValue(value)?.padStart(5, '0')
        this.value = valueString
        this.needCheck = needCheck
    }
}

data class NursingInstNumber(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class DoctorName(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class PatientName(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem {
    override fun initialize(value: Any?, needCheck: Boolean) {
        val valueString = ParserUtils.getStringValue(value)

        this.value = ParserUtils.removeParenthesis(valueString)
        this.needCheck = needCheck
    }
}

data class PatientRrn(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class MedicalInstName(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class LicenseNumber(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class DiseaseCodes(
    override var values: MutableList<String>? = mutableListOf(),
    override var needCheck: Boolean = true
) : CompositePrescriptionItem

data class PrescriptionRef(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem {
    override fun initialize(value: Any?, needCheck: Boolean) {
        val valueString = ParserUtils.getStringValue(value)
        this.value = PrescriptionRefTypeEnum.determineType(valueString).word
        this.needCheck = needCheck
    }
}

data class SelfPayCode(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class SelfPayRateCode(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem {
    override fun initialize(value: Any?, needCheck: Boolean) {
        val valueString = ParserUtils.getStringValue(value)
        this.value = SelfPayRateCodeEnum.fromInference(valueString)
        this.needCheck = needCheck
    }
}

data class DrugCode(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class DrugName(
    override var value: String? = null,
    override var needCheck: Boolean = true
) : SinglePrescriptionItem

data class OneDose(
    override var value: String = "1",
    override var needCheck: Boolean = true
) : DosePrescriptionItem

data class DosingPerDay(
    override var value: String = "1",
    override var needCheck: Boolean = true
) : DosePrescriptionItem

data class TotalDosingDays(
    override var value: String = "1",
    override var needCheck: Boolean = true
) : DosePrescriptionItem