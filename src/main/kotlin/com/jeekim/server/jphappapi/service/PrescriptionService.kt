package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.client.lomin.LominAdapter
import com.jeekim.server.jphappapi.client.lomin.data.LominOcrRequest
import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.PrescriptionParser
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.Base64
import java.util.UUID

@Service
class PrescriptionService(
    private val lominAdapter: LominAdapter,
) {
    fun postOcr(prescription: MultipartFile, hospitalId: String): OcrResponse? {
        val uuid = UUID.randomUUID().toString()
        val fileName = "${uuid}.jpg"
        val request = LominOcrRequest.of(encodeFile(prescription), fileName)
        val lominOcrResponse =  lominAdapter.postOcr(request)
        return PrescriptionParser(lominOcrResponse).parsePostInferenceResponse()
    }

    fun encodeFile(prescription: MultipartFile): String {
        return try {
            Base64.getEncoder().encodeToString(prescription.bytes)
        } catch (e: IOException) {
            throw JphBizException(ErrorCode.ENCODE_FILE_ERROR)
        }
    }
}