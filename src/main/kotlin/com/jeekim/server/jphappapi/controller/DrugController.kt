package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesBySmsRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.data.SendMyDrugHistoriesRequest
import com.jeekim.server.jphappapi.data.SmsRefreshRequest
import com.jeekim.server.jphappapi.data.SmsSendRequest
import com.jeekim.server.jphappapi.exception.ErrorResponse
import com.jeekim.server.jphappapi.service.DrugService
import com.jeekim.server.jphappapi.utils.logger
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/drug")
@Tag(name = "Drug", description = "약 조회 API")
class DrugController(
    private val drugService: DrugService
) {

    @PostMapping("/easy")
    @Operation(summary = "내가 먹은 약 조회(간편 인증)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다."),
        ApiResponse(responseCode = "500", description = "[200001] 인포텍 API 호출 에러")
    )
    fun getMyDrugHistoriesEasy(
        @Valid @RequestBody(required = true) request: GetMyDrugHistoriesByKakaoRequest
    ): GetMyDrugHistoriesResponse {
        val userInfo = request.toUserInfo()
        return drugService.getMyDrugHistoriesByEasyLogin(request).toResponse(userInfo)
    }
    @PostMapping("/sms/send")
    @Operation(summary = "내가 먹은 약 조회(SMS) - 인증 코드 전송")
    fun smsSend(@Valid @RequestBody(required = true) request: SmsSendRequest) {
        drugService.sendSms(request)
    }

    @PostMapping("/sms/refresh")
    @Operation(summary = "내가 먹은 약 조회(SMS) - refresh")
    fun smsValidate(@Valid @RequestBody(required = true) request: SmsRefreshRequest) {
        drugService.refreshSms(request)
    }
    @PostMapping("/sms")
    @Operation(summary = "내가 먹은 약 조회(SMS)")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다."),
        ApiResponse(responseCode = "500", description = "[200001] 인포텍 API 호출 에러")
    )
    fun getMyDrugHistoriesSms(
        @Valid @RequestBody(required = true) request: GetMyDrugHistoriesBySmsRequest
    ): GetMyDrugHistoriesResponse {
        val userInfo = request.toUserInfo()
        return drugService.getMyDrugHistoriesBySmsLogin(request).toResponse(userInfo)
    }

    @Operation(summary = "내가 먹은 약 KIMS 전송")
    @PostMapping("/send")
    fun sendMyDrugHistories(@RequestBody request: SendMyDrugHistoriesRequest) {
        drugService.sendMyDrugHistoriesByApi(request)
    }
}