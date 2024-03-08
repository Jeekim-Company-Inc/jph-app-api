package com.jeekim.server.jphappapi.model.prescription

import com.jeekim.server.jphappapi.client.lomin.model.DosingPerDay
import com.jeekim.server.jphappapi.client.lomin.model.DrugCode
import com.jeekim.server.jphappapi.client.lomin.model.DrugName
import com.jeekim.server.jphappapi.client.lomin.model.OneDose
import com.jeekim.server.jphappapi.client.lomin.model.SelfPayRateCode
import com.jeekim.server.jphappapi.client.lomin.model.TotalDosingDays

data class PrescriptionContent(
    var selfPayRateCode: SelfPayRateCode = SelfPayRateCode(),
    var drugCode: DrugCode = DrugCode(),
    var drugName: DrugName = DrugName(),
    var oneDose: OneDose = OneDose(),
    var dosingPerDay: DosingPerDay = DosingPerDay(),
    var totalDosingDays: TotalDosingDays = TotalDosingDays()
)