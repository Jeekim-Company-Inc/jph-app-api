package com.jeekim.server.jphappapi.client.lomin

import com.jeekim.server.jphappapi.client.lomin.data.LominAuthRequest
import com.jeekim.server.jphappapi.client.lomin.data.LominOcrRequest
import com.jeekim.server.jphappapi.client.lomin.data.LominOcrResponse
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.HttpUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class LominAdapter(
    private val lominClient: LominClient,
    @Value("\${lomin.email}") private val email: String,
    @Value("\${lomin.password}") private val password: String
) {
    fun getAuth(): String {
        val request = LominAuthRequest(email, password)
        val form = HttpUtils.convert(request)
        return lominClient.auth(form).accessToken
    }

    fun postOcr(request: LominOcrRequest): LominOcrResponse {
        val token = getAuth()

        return lominClient.ocr(request, "Bearer $token")
    }
}