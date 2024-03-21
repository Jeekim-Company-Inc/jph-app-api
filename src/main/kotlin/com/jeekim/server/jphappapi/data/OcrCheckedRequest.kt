package com.jeekim.server.jphappapi.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.model.KimsInputType
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

data class OcrCheckedRequest (
    @JsonProperty("patientCategory")
    val patientCategory: String?,
    @JsonProperty("patientName")
    val patientName: String?,
    @JsonProperty("issuanceDate")
    @field:NotNull
    val issuanceDate: LocalDate,
    @JsonProperty("issuanceNumber")
    val issuanceNumber: String?,
    @JsonProperty("patientRrn")
    val patientRrn: String?,
    @JsonProperty("selfPayCode")
    val selfPayCode: String?,
    @JsonProperty("doctorName")
    val doctorName: String?,
    @JsonProperty("medicalInstName")
    val medicalInstName: String?,
    @JsonProperty("nursingInstNumber")
    val nursingInstNumber: String?,
    @JsonProperty("licenseNumber")
    val licenseNumber: String?,
    @JsonProperty("diseaseCodes")
    val diseaseCodes: List<String>,
    @JsonProperty("prescriptionRef")
    val prescriptionRef: String?,
    @JsonProperty("internalPrescriptionContents")
    var internalPrescriptionContents: List<PrescriptionContentRequest>,
    @JsonProperty("injectionPrescriptionContents")
    var injectionPrescriptionContents: List<PrescriptionContentRequest>,
    @JsonProperty("fileKey")
    val fileKey: String? = null,
){
    fun validate(){
        this.injectionPrescriptionContents = this.injectionPrescriptionContents.filter {
            it.drugCode != null && it.drugName != null
        }
        this.internalPrescriptionContents = this.internalPrescriptionContents.filter {
            it.drugCode != null && it.drugName != null
        }
    }
    data class PrescriptionContentRequest(
        val selfPayRateCode: String,
        val drugCode: String?,
        val drugName: String?,
        val oneDose: String,
        val dosingPerDay: String,
        val totalDosingDays: String
    )
    fun toCommand(customerId: String): KimsDrugHistorySendRequest{
        val dataType = KimsInputType.OCR.ordinal

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
                patientNo = patientRrn ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "환자 주민번호가 없습니다"),
                patientName = patientName ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "환자 이름이 없습니다"),
                prescriptions = listOf(
                    KimsDrugHistorySendRequest.RxPrescription(
                        hospNum = nursingInstNumber ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "요양기관 변호가 없습니다"),
                        hospName = medicalInstName ?: throw JphBizException(ErrorCode.INPUT_NOT_VALID, "처방 병원이 없습니다"),
                        hospDate = issuanceDate.toString(),
                        pharmaDate = issuanceDate.toString(),
                        drugs = internalDrugs + injectionDrugs
                    )
                )
            )
        )
    }
}