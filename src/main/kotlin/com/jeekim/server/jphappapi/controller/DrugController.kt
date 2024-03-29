package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesBySmsRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.data.OcrCheckedRequest
import com.jeekim.server.jphappapi.data.SendMyDrugHistoriesRequest
import com.jeekim.server.jphappapi.data.SmsSendRequest
import com.jeekim.server.jphappapi.client.infotech.data.SmsSendResponse
import com.jeekim.server.jphappapi.exception.ErrorResponse
import com.jeekim.server.jphappapi.service.DrugService
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
@Tag(name = "약 조회 및 전송 API", description = "약 조회 및 전송 API")
class DrugController(
    private val drugService: DrugService
) {

    @PostMapping("/easy")
    @Operation(summary = "내가 먹은 약 조회(간편 인증)")
    @ApiResponses(
       value = [
           ApiResponse(responseCode = "200", description = "OK"),
           ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
           ApiResponse(responseCode = "500", description = "[200001] 인포텍 API 호출 에러", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
       ]
    )
    fun getMyDrugHistoriesEasy(
        @Valid @RequestBody(required = true) request: GetMyDrugHistoriesByKakaoRequest
    ): GetMyDrugHistoriesResponse {
        val userInfo = request.toUserInfo()
        return drugService.getMyDrugHistoriesByEasyLogin(request).toResponse(userInfo)
    }
    @PostMapping("/sms/send")
    @Operation(summary = "SMS 문자 전송")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
            ApiResponse(responseCode = "500", description = "[200001] 인포텍 API 호출 에러", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    fun smsSend(@Valid @RequestBody(required = true) request: SmsSendRequest): SmsSendResponse {
        return drugService.sendSms(request)
    }

    @PostMapping("/sms")
    @Operation(summary = "내가 먹은 약 조회(SMS)")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = GetMyDrugHistoriesResponse::class))]),
            ApiResponse(responseCode = "400", description = "[200005] SMS 인증 코드 불일치 \n\n [300001] 입력값이 올바르지 않습니다.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
            ApiResponse(responseCode = "500", description = "[200001] 인포텍 API 호출 에러", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    fun getMyDrugHistoriesSms(
        @Valid @RequestBody(required = true) request: GetMyDrugHistoriesBySmsRequest
    ): GetMyDrugHistoriesResponse {
        val userInfo = request.toUserInfo()
        return drugService.getMyDrugHistoriesBySmsLogin(request).toResponse(userInfo)
    }

    @Operation(summary = "내가 먹은 약 KIMS 전송 - API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK"),
            ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
            ApiResponse(responseCode = "500", description = "[200003] 킴스 API 호출 에러", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @PostMapping("/api/send")
    fun sendMyDrugHistoriesByApi(@RequestBody @Valid request: SendMyDrugHistoriesRequest) {
        drugService.sendMyDrugHistoriesByApi(request)
    }
}