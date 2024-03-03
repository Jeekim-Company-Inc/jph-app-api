package com.jeekim.server.jphappapi.client.infotech

import com.jeekim.server.jphappapi.client.infotech.data.InfoTechEasyLoginRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfoTechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfoTechSmsLoginRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "info-tech-client",
    url = "\${infotech.host}",
    configuration = [InfoTechClientConfiguration::class]
)
interface InfoTechClient {

    @PostMapping
    fun getMyDrugHistoriesEasy(
        @RequestBody request: InfoTechEasyLoginRequest
    ): InfoTechMyDrugHistoriesResponse

    @PostMapping
    fun getMyDrugHistoriesSms(
        @RequestBody request: InfoTechSmsLoginRequest
    ): InfoTechMyDrugHistoriesResponse
}