package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.LoginRequest
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.Hospital.HOSPITAL
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
class HospitalController{
    @PostMapping("/login")
    @Operation(summary = "병원 로그인")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "401", description = "[000003] 병원코드 불일치")
    )
    fun login(@RequestBody request: LoginRequest){
        val id = request.id
        val code = request.code
        HOSPITAL.firstOrNull { it.id == id }?.let {
            if(it.code != code){
                throw JphBizException(ErrorCode.HOSPITAL_CODE_NOT_MATCH)
            }
        } ?: throw JphBizException(ErrorCode.HOSPITAL_NOT_FOUND)
    }
}
