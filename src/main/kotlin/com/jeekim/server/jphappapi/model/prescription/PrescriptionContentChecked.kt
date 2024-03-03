package com.jeekim.server.jphappapi.model.prescription

data class PrescriptionContentChecked(
    val selfPayRateCode: String?,
    val drugCode: String?,
    val drugName: String?,
    val oneDose: String?,
    val dosingPerDay: String?,
    val totalDosingDays: String?,
)