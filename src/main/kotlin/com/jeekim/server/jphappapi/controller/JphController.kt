package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.ImageUrl
import com.jeekim.server.jphappapi.utils.logger
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
@Hidden
class JphController{

    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }

    @GetMapping("/test")
    fun test(): String {
        logger().info("동작 테스트")
        return "test"
    }
}