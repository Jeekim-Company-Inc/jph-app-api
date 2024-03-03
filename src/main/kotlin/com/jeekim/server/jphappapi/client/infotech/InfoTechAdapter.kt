package com.jeekim.server.jphappapi.client.infotech


import com.jeekim.server.jphappapi.client.infotech.data.InfoTechEasyLoginRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfoTechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfoTechSmsLoginRequest
import org.springframework.stereotype.Component

@Component
class InfoTechAdapter(
    private val infoTechClient: InfoTechClient
) {

    fun getMyDrugHistoriesByEasyLogin(request: InfoTechEasyLoginRequest): InfoTechMyDrugHistoriesResponse {
        return infoTechClient.getMyDrugHistoriesEasy(request)
    }
    fun getMyDrugHistoriesBySmsLogin(request: InfoTechSmsLoginRequest): InfoTechMyDrugHistoriesResponse {
        return infoTechClient.getMyDrugHistoriesSms(request)
    }

}