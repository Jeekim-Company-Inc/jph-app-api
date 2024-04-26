package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.client.infotech.InfotechAdapter
import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyVerifyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechSmsRequest
import com.jeekim.server.jphappapi.client.kims.KimsAdapter
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesBySmsRequest
import com.jeekim.server.jphappapi.data.SendMyDrugHistoriesRequest
import com.jeekim.server.jphappapi.data.SmsSendRequest
import com.jeekim.server.jphappapi.client.infotech.data.SmsSendResponse
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoVerifyRequest
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import org.springframework.stereotype.Service

@Service
class DrugService(
    private val kimsAdapter: KimsAdapter,
    private val infotechAdapter: InfotechAdapter,
) {

    fun getMyDrugHistoriesByEasyLogin(request: GetMyDrugHistoriesByKakaoRequest): InfotechEasyResponse {
        val easyLoginRequest = InfotechEasyRequest.of(request)
        val result = runCatching { infotechAdapter.getMyDrugHistoriesByEasyLogin(easyLoginRequest) }.getOrNull()
            ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        if(result.out.errYn == "N"){ return result }
        val errorMsg = result.out.errMsg
        if(errorMsg.contains("LOGIN-004")){ throw JphBizException(ErrorCode.EASY_LOGIN_INPUT_NOT_VALID, errorMsg) }
        throw JphBizException(ErrorCode.INFOTECH_API_ERROR, result.out.errMsg)
    }
    fun getMyDrugHistoriesByEasyLoginVerify(request: GetMyDrugHistoriesByKakaoVerifyRequest): InfotechMyDrugHistoriesResponse {
        val easyLoginRequest = InfotechEasyVerifyRequest.of(request)
        val result = runCatching { infotechAdapter.getMyDrugHistoriesByEasyLoginVerify(easyLoginRequest) }.getOrNull()
            ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        if(result.out.errYn == "N"){ return result }
        if(result.out.errMsg.contains("LOGIN-005")){ throw JphBizException(ErrorCode.EASY_LOGIN_EXPIRED, result.out.errMsg) }
        if(result.out.errMsg.contains("LOGIN-003")){ throw JphBizException(ErrorCode.EASY_LOGIN_NOT_AUTHORIZED_YET, result.out.errMsg) }
        throw JphBizException(ErrorCode.INFOTECH_API_ERROR, result.out.errMsg)
    }
    fun getMyDrugHistoriesBySmsLogin(request: GetMyDrugHistoriesBySmsRequest): InfotechMyDrugHistoriesResponse {
        val smsLoginRequest = InfotechSmsRequest.of(request)
        val result = runCatching { infotechAdapter.getMyDrugHistoriesBySmsLogin(smsLoginRequest) }
        return processInfotechSmsResponse(result)
    }

    fun sendSms(request: SmsSendRequest): SmsSendResponse {
        val smsSendRequest = InfotechSmsRequest.of(request)
        val result = runCatching { infotechAdapter.sendSms(smsSendRequest) }.getOrNull()
            ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        if(result.out.errYn == "N"){ return result }
        throw JphBizException(ErrorCode.INFOTECH_API_ERROR, result.out.errMsg)
    }

    fun sendMyDrugHistoriesByApi(request: SendMyDrugHistoriesRequest, id: String){
        val customerId = if(id == "test") "PAKUAS" else id
        val sendRequest = request.toCommandList(customerId)
        kimsAdapter.sendMyDrugHistories(sendRequest)
    }

    private fun processInfotechSmsResponse(response: Result<InfotechMyDrugHistoriesResponse>): InfotechMyDrugHistoriesResponse{
        val result = response.getOrNull() ?: throw JphBizException(ErrorCode.INFOTECH_API_ERROR)
        val errYn = result.out.errYn
        val errMsg = result.out.errMsg
        if(errYn == "N"){ return result }
        if(errMsg.contains("I0001-V01")){ throw JphBizException(ErrorCode.SMS_CODE_NOT_MATCH, errMsg) }
        throw JphBizException(ErrorCode.INFOTECH_API_ERROR, errMsg)
    }
}
