package com.jeekim.server.jphappapi.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.model.KimsInputType
import com.jeekim.server.jphappapi.model.prescription.Bbox
import com.jeekim.server.jphappapi.model.prescription.CompositeItem
import com.jeekim.server.jphappapi.model.prescription.IssuanceDate
import com.jeekim.server.jphappapi.model.prescription.PatientCategory
import com.jeekim.server.jphappapi.model.prescription.PatientName
import com.jeekim.server.jphappapi.model.prescription.PrescriptionContent
import com.jeekim.server.jphappapi.model.prescription.PrescriptionRef
import com.jeekim.server.jphappapi.model.prescription.SingleBboxItem
import com.jeekim.server.jphappapi.model.prescription.SingleItem
import com.jeekim.server.jphappapi.utils.hashSHA512
import java.util.UUID

data class OcrCheckedRequest (
    @JsonProperty("patientCategory")
    val patientCategory: PatientCategory = PatientCategory(),
    @JsonProperty("patientName")
    val patientName: PatientName = PatientName(),
    @JsonProperty("issuanceDate")
    val issuanceDate: IssuanceDate = IssuanceDate(),
    @JsonProperty("issuanceNumber")
    val issuanceNumber: SingleItem = SingleItem(),
    @JsonProperty("patientRrn")
    val patientRrn: SingleBboxItem = SingleBboxItem(),
    @JsonProperty("selfPayCode")
    val selfPayCode: SingleItem = SingleItem(),
    @JsonProperty("doctorName")
    val doctorName: SingleBboxItem = SingleBboxItem(),
    @JsonProperty("medicalInstName")
    val medicalInstName: SingleItem = SingleItem(),
    @JsonProperty("nursingInstNumber")
    val nursingInstNumber: SingleItem = SingleItem(),
    @JsonProperty("licenseNumber")
    val licenseNumber: SingleBboxItem = SingleBboxItem(),
    @JsonProperty("diseaseCodes")
    val diseaseCodes: CompositeItem = CompositeItem(),
    @JsonProperty("prescriptionRef")
    val prescriptionRef: PrescriptionRef = PrescriptionRef(),
    @JsonProperty("internalPrescriptionContents")
    var internalPrescriptionContents: List<PrescriptionContent> = emptyList(),
    @JsonProperty("injectionPrescriptionContents")
    var injectionPrescriptionContents: List<PrescriptionContent> = emptyList(),
    @JsonProperty("extraPersonalInfoBboxList")
    val extraPersonalInfoBboxList: MutableList<Bbox> = mutableListOf(),
    @JsonProperty("fileKey")
    val fileKey: String? = null,
){
    fun toCommand(customerId: String): KimsDrugHistorySendRequest{
        val dataType = KimsInputType.OCR.ordinal
        val rrn = patientRrn.value ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "환자 주민 번호 없음")
        val name = patientName.value ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "환자 이름 없음")
        val nursingInstNumber = nursingInstNumber.value ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "처방 병원 요양 기관 번호 없음")
        val medicalInstName = medicalInstName.value ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "처방 병원 이름 없음")
        val issuanceDate = issuanceDate.value ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "처방 발행일 없음")
        val internalDrugs = internalPrescriptionContents.map {
            KimsDrugHistorySendRequest.RxDrug.ofInternal(it)
        }
        val injectionDrugs = injectionPrescriptionContents.map {
            KimsDrugHistorySendRequest.RxDrug.ofInjection(it)
        }
        return KimsDrugHistorySendRequest(
            custID = customerId,
            dataType = dataType,
            rxData = KimsDrugHistorySendRequest.RxData(
                patientNo = rrn,
                patientName = name,
                prescription = listOf(
                    KimsDrugHistorySendRequest.RxPrescription(
                        hospNum = nursingInstNumber,
                        hospName = medicalInstName,
                        hospDate = issuanceDate,
                        pharmaDate = issuanceDate,
                        drugs = internalDrugs + injectionDrugs
                    )
                )
            )
        )
    }
}