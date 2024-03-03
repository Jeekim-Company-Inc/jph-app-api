package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.ImageUrl
import com.jeekim.server.jphappapi.data.LoginRequest
import com.jeekim.server.jphappapi.service.HospitalService
import com.jeekim.server.jphappapi.service.ManageService
import com.jeekim.server.jphappapi.service.DocumentService
import com.jeekim.server.jphappapi.utils.logger
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// 인증 x
@RestController
@RequestMapping("/hospital")
class HospitalController(
    private val hospitalService: HospitalService,

) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest){
        hospitalService.login(request.id, request.code)
    }

}