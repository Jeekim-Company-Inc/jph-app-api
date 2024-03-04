package com.jeekim.server.jphappapi.client.kims

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import com.jeekim.server.jphappapi.client.kims.data.KimsSendCheckHistoryRequeset
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "kims-client",
    url = "\${kims.host}",
    configuration = [KimsClientConfiguration::class, KimsErrorDecoder::class]
)
interface KimsClient {

    @PostMapping("/SelfMedi/SetRxData")
    fun sendMyDrugHistories(
        @RequestBody request: KimsDrugHistorySendRequest
    )

    @PostMapping("/SelfMedi/CheckRxData")
    fun checkMyDrugSend(
        @RequestBody request: KimsSendCheckHistoryRequeset
    ): JsonNode
}