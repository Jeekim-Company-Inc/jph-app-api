package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.data.OcrCheckedRequest
import com.jeekim.server.jphappapi.data.OcrRequest
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.service.DrugService
import com.jeekim.server.jphappapi.service.PrescriptionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/prescription")
@Tag(name = "처방전 API", description = "처방전 API")
class PrescriptionController(
    private val prescriptionService: PrescriptionService
) {

    @PostMapping("/ocr")
    @Operation(summary = "처방전 인식")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다."),
        ApiResponse(responseCode = "500", description = "[200002] 로민 API 호출 에러")
    )
    fun postOcr(@RequestBody request: OcrRequest, @RequestAttribute id: String): OcrResponse {
        return prescriptionService.postOcr(request, id) ?: throw JphBizException(ErrorCode.LOMIN_API_ERROR)
    }
}