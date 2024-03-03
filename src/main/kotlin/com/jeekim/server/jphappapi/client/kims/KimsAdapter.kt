package com.jeekim.server.jphappapi.client.kims

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistoryCheckRequest
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import org.springframework.stereotype.Component

@Component
class KimsAdapter(
    private val kimsClient: KimsClient,
) {
    fun sendMyDrugHistories(request: KimsDrugHistorySendRequest): JsonNode {
        return kimsClient.sendMyDrugHistories(request)
    }
    fun checkMyDrugHistories(request: KimsDrugHistoryCheckRequest): JsonNode {
        return kimsClient.checkMyDrugHistories(request)
    }
}