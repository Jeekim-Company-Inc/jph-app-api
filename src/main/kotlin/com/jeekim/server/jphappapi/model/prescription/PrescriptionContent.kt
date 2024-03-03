package com.jeekim.server.jphappapi.model.prescription

data class PrescriptionContent(
    var selfPayRateCode: SelfPayRateCode = SelfPayRateCode(),
    var drugCode: SingleItem = SingleItem(),
    var drugName: SingleItem = SingleItem(),
    val oneDose: DoseItem = DoseItem(),
    val dosingPerDay: DoseItem = DoseItem(),
    val totalDosingDays: DoseItem = DoseItem(),
){
    fun toChecked(){
        PrescriptionContentChecked(
            selfPayRateCode = selfPayRateCode.value,
            drugCode = drugCode.value,
            drugName = drugName.value,
            oneDose = oneDose.value,
            dosingPerDay = dosingPerDay.value,
            totalDosingDays = totalDosingDays.value,
        )
    }
}