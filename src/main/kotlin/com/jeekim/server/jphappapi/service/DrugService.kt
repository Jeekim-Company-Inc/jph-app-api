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
import com.jeekim.server.jphappapi.data.SmsSendRequest
import com.jeekim.server.jphappapi.data.SmsSendResponse
import com.jeekim.server.jphappapi.data.SmsVerifyRequest
import com.jeekim.server.jphappapi.data.SmsVerifyResponse
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.logger
import org.springframework.stereotype.Service

@Service
class DrugService(
    private val kimsAdapter: KimsAdapter,
    private val infotechAdapter: InfotechAdapter,
) {

    fun getMyDrugHistoriesByEasyLogin(request: GetMyDrugHistoriesByKakaoRequest): InfotechMyDrugHistoriesResponse {
        val easyLoginRequest = InfotechEasyRequest.of(request)
        return runCatching { infotechAdapter.getMyDrugHistoriesByEasyLogin(easyLoginRequest) }.getOrNull()
            ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
    }

    fun sendSms(request: SmsSendRequest): SmsSendResponse {
        val smsSendRequest = InfotechSmsRequest.of(request)
        val result = runCatching { infotechAdapter.sendSms(smsSendRequest) }.getOrNull()
            ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        val errYn = result.out.errYn
        if(errYn == "Y"){
            throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        }
        return result
    }

    fun getMyDrugHistoriesBySmsLogin(request: GetMyDrugHistoriesBySmsRequest): InfotechMyDrugHistoriesResponse {
        val smsLoginRequest = InfotechSmsRequest.of(request)
        val result = runCatching { infotechAdapter.getMyDrugHistoriesBySmsLogin(smsLoginRequest) }.getOrNull()
            ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        val errYn = result.out.errYn
        val errMsg = result.out.errMsg
        if(errYn == "Y"){
            if(errMsg.contains("I0001-004")){
                throw JphBizException(ErrorCode.SMS_CODE_NOT_MATCH)
            }
            throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        }
        return result

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