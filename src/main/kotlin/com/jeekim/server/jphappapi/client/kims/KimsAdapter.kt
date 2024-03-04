package com.jeekim.server.jphappapi.client.kims

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import com.jeekim.server.jphappapi.client.kims.data.KimsSendCheckHistoryRequeset
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.logger
import org.springframework.stereotype.Component

@Component
class KimsAdapter(
    private val kimsClient: KimsClient,
) {
    fun sendMyDrugHistories(request: KimsDrugHistorySendRequest){
        kimsClient.sendMyDrugHistories(request)
    }
    fun checkSend(rrn: String): JsonNode {
        logger().info("KIMS 약 처방 내역 전송 요청: {}", rrn)
        val request = KimsSendCheckHistoryRequeset(rrn, "PAKUAS")
        return kimsClient.checkMyDrugSend(request)
    }
}