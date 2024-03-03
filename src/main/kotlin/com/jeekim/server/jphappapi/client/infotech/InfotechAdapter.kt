package com.jeekim.server.jphappapi.client.infotech


import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechSmsRequest
import org.springframework.stereotype.Component

@Component
class InfotechAdapter(
    private val infotechClient: InfotechClient,
) {

    fun getMyDrugHistoriesByEasyLogin(request: InfotechEasyRequest): InfotechMyDrugHistoriesResponse {
        return infotechClient.getMyDrugHistoriesEasy(request)
    }

    fun getMyDrugHistoriesBySmsLogin(request: InfotechSmsRequest): InfotechMyDrugHistoriesResponse {
        return infotechClient.getMyDrugHistoriesSms(request)
    }

    fun processSms(request: InfotechSmsRequest) {
        infotechClient.getMyDrugHistoriesSms(request)
    }

}