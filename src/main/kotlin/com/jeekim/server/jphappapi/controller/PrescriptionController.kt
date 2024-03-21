package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.ErrorResponse
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.service.PrescriptionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/prescription")
@Tag(name = "처방전 API", description = "처방전 API")
class PrescriptionController(
    private val prescriptionService: PrescriptionService
) {

    @PostMapping("/ocr", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "처방전 인식")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "400", description = "[300001] 입력값이 올바르지 않습니다.", content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
        ApiResponse(responseCode = "500", description = "[200002] 로민 API 호출 에러", content = [Content(schema = Schema(implementation = ErrorResponse::class))])
    )
    fun postOcr(@RequestPart prescription: MultipartFile, @RequestAttribute id: String): OcrResponse {
        return prescriptionService.postOcr(prescription, id) ?: throw JphBizException(ErrorCode.LOMIN_API_ERROR)
    }
}