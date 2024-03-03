package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.data.OcrCheckedRequest
import com.jeekim.server.jphappapi.data.OcrRequest
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.service.DrugService
import com.jeekim.server.jphappapi.service.PrescriptionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/prescription")
class PrescriptionController(
    private val prescriptionService: PrescriptionService,
    private val drugService: DrugService
) {
    @PostMapping("/ocr")
    fun postOcr(@RequestBody request: OcrRequest, @RequestAttribute id: String, ): OcrResponse {
        return prescriptionService.postOcr(request, id) ?: throw JphBizException(ErrorCode.LOMIN_API_ERROR)
    }

    @PostMapping("/ocr/checked")
    fun postOcrChecked(@RequestBody request: OcrCheckedRequest){
//        return drugService.sendMyDrugHistoriesByApi(request)
    }
}