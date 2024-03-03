package com.jeekim.server.jphappapi.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.model.prescription.Bbox
import com.jeekim.server.jphappapi.model.prescription.CompositeItem
import com.jeekim.server.jphappapi.model.prescription.IssuanceDate
import com.jeekim.server.jphappapi.model.prescription.PatientCategory
import com.jeekim.server.jphappapi.model.prescription.PatientName
import com.jeekim.server.jphappapi.model.prescription.PrescriptionContent
import com.jeekim.server.jphappapi.model.prescription.PrescriptionRef
import com.jeekim.server.jphappapi.model.prescription.SingleBboxItem
import com.jeekim.server.jphappapi.model.prescription.SingleItem

data class OcrResponse (
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
)