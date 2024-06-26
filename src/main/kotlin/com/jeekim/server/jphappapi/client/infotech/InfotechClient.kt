package com.jeekim.server.jphappapi.client.infotech

import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechEasyVerifyRequest
import com.jeekim.server.jphappapi.client.infotech.data.InfotechMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.client.infotech.data.InfotechSmsRequest
import com.jeekim.server.jphappapi.client.infotech.data.SmsSendResponse
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
    ): InfotechEasyResponse

    @PostMapping
    fun getMyDrugHistoriesEasyVerify(
        @RequestBody request: InfotechEasyVerifyRequest
    ): InfotechMyDrugHistoriesResponse

    @PostMapping
    fun getMyDrugHistoriesSms(
        @RequestBody request: InfotechSmsRequest
    ): InfotechMyDrugHistoriesResponse

    @PostMapping
    fun sendSms(
        @RequestBody request: InfotechSmsRequest
    ): SmsSendResponse
}