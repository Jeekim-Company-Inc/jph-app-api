package com.jeekim.server.jphappapi.service

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.client.infotech.InfotechAdapter
import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechSmsRequest
import com.jeekim.server.jphappapi.client.kims.KimsAdapter
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesBySmsRequest
import com.jeekim.server.jphappapi.data.OcrCheckedRequest
import com.jeekim.server.jphappapi.data.SendMyDrugHistoriesRequest
import com.jeekim.server.jphappapi.data.SmsRefreshRequest
import com.jeekim.server.jphappapi.data.SmsSendRequest
import org.springframework.stereotype.Service

@Service
class DrugService(
    private val kimsAdapter: KimsAdapter,
    private val infotechAdapter: InfotechAdapter,
) {

    fun getMyDrugHistoriesByEasyLogin(request: GetMyDrugHistoriesByKakaoRequest): InfotechMyDrugHistoriesResponse {
        val easyLoginRequest = InfotechEasyRequest.of(request)
        return infotechAdapter.getMyDrugHistoriesByEasyLogin(easyLoginRequest)
    }


    fun sendSms(request: SmsSendRequest) {
        val smsSendRequest = InfotechSmsRequest.of(request)
        infotechAdapter.processSms(smsSendRequest)
    }
    fun refreshSms(request: SmsRefreshRequest) {
        val smsRefreshRequest = InfotechSmsRequest.of(request)
        infotechAdapter.processSms(smsRefreshRequest)
    }
    fun getMyDrugHistoriesBySmsLogin(request: GetMyDrugHistoriesBySmsRequest): InfotechMyDrugHistoriesResponse {
        val smsLoginRequest = InfotechSmsRequest.of(request)
        return infotechAdapter.getMyDrugHistoriesBySmsLogin(smsLoginRequest)
    }

    fun sendMyDrugHistoriesByApi(request: SendMyDrugHistoriesRequest): List<JsonNode> {
        val sendList = request.toCommandList("test")
        return sendList.map { kimsAdapter.sendMyDrugHistories(it) }
    }
    fun sendMyDrugHistoriesByOcr(request: OcrCheckedRequest){
//        val sendList = request.toCommandList("test")
//        return sendList.map {
//            logger().info("request: $it")
//            kimsAdapter.sendMyDrugHistories(it)
//        }
    }
}