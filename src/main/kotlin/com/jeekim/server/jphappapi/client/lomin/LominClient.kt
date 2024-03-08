package com.jeekim.server.jphappapi.client.lomin

import com.jeekim.server.jphappapi.client.lomin.data.LominAuthResponse
import com.jeekim.server.jphappapi.client.lomin.data.LominOcrRequest
import com.jeekim.server.jphappapi.client.lomin.data.LominOcrResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "lomin-client",
    url = "\${lomin.baseUrl}",
    configuration = [LominClientConfiguration::class]
)
interface LominClient {
    @PostMapping("/auth", produces = ["application/x-www-form-urlencoded"])
    fun auth(@RequestBody request: MultiValueMap<String, String>): LominAuthResponse


    @PostMapping("/inference/docx-cls-kv")
    fun ocr(@RequestBody request: LominOcrRequest, @RequestHeader("Authorization") token: String): LominOcrResponse

}