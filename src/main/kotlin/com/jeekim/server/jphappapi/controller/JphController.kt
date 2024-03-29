package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.ImageUrl
import com.jeekim.server.jphappapi.service.DocumentService
import com.jeekim.server.jphappapi.utils.logger
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
@Tag(name = "공통/어드민 API")
class JphController(
    private val documentService: DocumentService
){
    @Hidden
    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }
    @Hidden
    @GetMapping("/test")
    fun test(): String {
        logger().info("동작 테스트")
        return "test"
    }
    @Operation(summary = "약관 이미지 조회", description = "요청 받은 약관 이미지 전달")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = [Content(array = ArraySchema(schema = Schema(implementation = ImageUrl::class)))]
    )
    @ApiResponse(responseCode = "404", description = "[100001] 약관 폴더명 불일치 \n\n[200249] 약관 이미지 존재하지 않음")
    @GetMapping("/document/terms")
    fun getTerms(@RequestParam(value = "types") types: List<String>): List<ImageUrl> {
        return documentService.getTermsImages(types)
    }
}