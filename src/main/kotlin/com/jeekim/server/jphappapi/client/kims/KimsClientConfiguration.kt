package com.jeekim.server.jphappapi.client.kims

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class KimsClientConfiguration {
    @Bean
    fun kimsClientRequestInterceptor(
        @Value("\${kims.username}") username: String,
        @Value("\${kims.secret}") secret: String
    ): RequestInterceptor {
        val toEncode = "$username:$secret"
        val encoded = "Basic " + java.util.Base64.getEncoder().encodeToString(toEncode.toByteArray())
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Authorization", encoded)
        }
    }
}