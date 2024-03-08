package com.jeekim.server.jphappapi.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.client.lomin.model.DiseaseCodes
import com.jeekim.server.jphappapi.client.lomin.model.DoctorName
import com.jeekim.server.jphappapi.client.lomin.model.IssuanceDate
import com.jeekim.server.jphappapi.client.lomin.model.IssuanceNumber
import com.jeekim.server.jphappapi.client.lomin.model.LicenseNumber
import com.jeekim.server.jphappapi.client.lomin.model.MedicalInstName
import com.jeekim.server.jphappapi.client.lomin.model.NursingInstNumber
import com.jeekim.server.jphappapi.client.lomin.model.PatientCategory
import com.jeekim.server.jphappapi.client.lomin.model.PatientName
import com.jeekim.server.jphappapi.client.lomin.model.PatientRrn
import com.jeekim.server.jphappapi.client.lomin.model.PrescriptionRef
import com.jeekim.server.jphappapi.client.lomin.model.SelfPayCode
import com.jeekim.server.jphappapi.model.prescription.PrescriptionContent
data class OcrResponse (
    val patientCategory: PatientCategory = PatientCategory(),
    val patientName: PatientName = PatientName(),
    val issuanceDate: IssuanceDate = IssuanceDate(),
    val issuanceNumber: IssuanceNumber = IssuanceNumber(),
    val patientRrn: PatientRrn = PatientRrn(),
    val selfPayCode: SelfPayCode = SelfPayCode(),
    val doctorName: DoctorName = DoctorName(),
    val medicalInstName: MedicalInstName = MedicalInstName(),
    val nursingInstNumber: NursingInstNumber = NursingInstNumber(),
    val licenseNumber: LicenseNumber = LicenseNumber(),
    val diseaseCodes: DiseaseCodes = DiseaseCodes(),
    val prescriptionRef: PrescriptionRef = PrescriptionRef(),
    var internalPrescriptionContents: List<PrescriptionContent> = emptyList(),
    var injectionPrescriptionContents: List<PrescriptionContent> = emptyList(),
    var fileKey: String? = null
)