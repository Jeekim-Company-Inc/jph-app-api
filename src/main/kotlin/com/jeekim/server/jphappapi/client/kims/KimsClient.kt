package com.jeekim.server.jphappapi.client.kims

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistoryCheckRequest
import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "kims-client",
    url = "\${kims.host}",
    configuration = [KimsClientConfiguration::class]
)
interface KimsClient {

    @PostMapping("/SelfMedi/SetRxData")
    fun sendMyDrugHistories(
        @RequestBody request: KimsDrugHistorySendRequest
    ): JsonNode

    @PostMapping("/SelfMedi/GetRxData")
    fun checkMyDrugHistories(
        @RequestBody request: KimsDrugHistoryCheckRequest
    ): JsonNode
}