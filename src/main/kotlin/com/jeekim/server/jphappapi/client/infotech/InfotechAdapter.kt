package com.jeekim.server.jphappapi.client.infotech


import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechSmsRequest
import com.jeekim.server.jphappapi.client.infotech.data.SmsSendResponse
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
    fun sendSms(request: InfotechSmsRequest): SmsSendResponse {
        return infotechClient.sendSms(request)
    }

}