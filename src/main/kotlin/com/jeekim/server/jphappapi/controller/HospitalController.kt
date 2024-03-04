package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.LoginRequest
import com.jeekim.server.jphappapi.service.HospitalService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// 인증 x
@RestController
@RequestMapping("/hospital")
@Tag(name = "병원 API", description = "병원 API")
class HospitalController(
    private val hospitalService: HospitalService,

) {
    @PostMapping("/login")
    @Operation(summary = "병원 로그인")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "[000003] 병원코드 불일치")
    )
    fun login(@RequestBody request: LoginRequest){
        hospitalService.login(request.id, request.code)
    }
}