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
import com.jeekim.server.jphappapi.data.SmsSendResponse
import com.jeekim.server.jphappapi.data.SmsVerifyRequest
import com.jeekim.server.jphappapi.data.SmsVerifyResponse
import com.jeekim.server.jphappapi.utils.logger
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

    fun sendSms(request: SmsSendRequest): SmsSendResponse {
        val smsSendRequest = InfotechSmsRequest.of(request)
        return infotechAdapter.sendSms(smsSendRequest)
    }

    fun verifySms(request: SmsVerifyRequest): SmsVerifyResponse {
        val smsSendRequest = InfotechSmsRequest.of(request)
        return infotechAdapter.verifySms(smsSendRequest)
    }
    fun refreshSms(request: SmsRefreshRequest): SmsSendResponse {
        val smsRefreshRequest = InfotechSmsRequest.of(request)
        return infotechAdapter.refreshSms(smsRefreshRequest)
    }
    fun getMyDrugHistoriesBySmsLogin(request: GetMyDrugHistoriesBySmsRequest): InfotechMyDrugHistoriesResponse {
        val smsLoginRequest = InfotechSmsRequest.of(request)
        return infotechAdapter.getMyDrugHistoriesBySmsLogin(smsLoginRequest)
    }

    fun sendMyDrugHistoriesByApi(request: SendMyDrugHistoriesRequest){
        val sendRequest = request.toCommandList("PAKUAS")
        kimsAdapter.sendMyDrugHistories(sendRequest)
    }
    fun sendMyDrugHistoriesByOcr(request: OcrCheckedRequest){
        val sendRequest = request.toCommand("PAKUAS")
        kimsAdapter.sendMyDrugHistories(sendRequest)
    }

    fun checkKimsSend(rrn: String): JsonNode{
        return kimsAdapter.checkSend(rrn)
    }
}