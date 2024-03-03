package com.jeekim.server.jphappapi.client.infotech

import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechSmsRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "info-tech-client",
    url = "\${infotech.host}",
    configuration = [InfotechClientConfiguration::class]
)
interface InfotechClient {

    @PostMapping
    fun getMyDrugHistoriesEasy(
        @RequestBody request: InfotechEasyRequest
    ): InfotechMyDrugHistoriesResponse

    @PostMapping
    fun getMyDrugHistoriesSms(
        @RequestBody request: InfotechSmsRequest
    ): InfotechMyDrugHistoriesResponse
}