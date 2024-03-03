package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.ImageUrl
import com.jeekim.server.jphappapi.service.DocumentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class JphController(
    private val documentService: DocumentService
) {

    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }
    @GetMapping("/terms")
    fun getTerms(): List<ImageUrl> {
        return documentService.getTerms()
    }
}