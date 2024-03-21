package com.jeekim.server.jphappapi.client.kims

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import com.jeekim.server.jphappapi.client.kims.data.KimsSendCheckHistoryRequeset
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.logger
import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.util.Base64

@Component
class KimsAdapter(
    private val kimsClient: KimsClient,
) {
    fun sendMyDrugHistories(request: KimsDrugHistorySendRequest){
        kimsClient.sendMyDrugHistories(request)
    }
}