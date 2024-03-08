package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.client.bucket.BucketAdapter
import com.jeekim.server.jphappapi.client.lomin.LominAdapter
import com.jeekim.server.jphappapi.client.lomin.data.LominOcrRequest
import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.entity.Prescription
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.repository.PrescriptionRepository
import com.jeekim.server.jphappapi.utils.PrescriptionParser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.time.LocalDate
import java.util.Base64
import java.util.UUID

@Service
class PrescriptionService(
    private val lominAdapter: LominAdapter,
    private val prescriptionRepository: PrescriptionRepository,
    private val bucketAdapter: BucketAdapter
) {
    @Transactional
    fun postOcr(prescription: MultipartFile, hospitalId: String): OcrResponse? {
        val today = LocalDate.now()
        val uuid = UUID.randomUUID().toString()
        val fileName = "${uuid}.jpg"
//        val filePath = "/prescriptions/${hospitalId}/${today.year}/${today.monthValue}/${today.dayOfMonth}/${fileName}"
//        val fileUrl = bucketAdapter.upload(prescription, filePath)
        val saved = prescriptionRepository.save(Prescription(hospitalId = hospitalId))
        val result = runCatching {
            val request = LominOcrRequest.of(encodeFile(prescription), fileName)
            val lominOcrResponse =  lominAdapter.postOcr(request)
            PrescriptionParser(lominOcrResponse).parsePostInferenceResponse()
        }.onFailure {
            saved.fail(it.message ?: "OCR 실패")
        }.onSuccess {
            saved.success()
        }.getOrNull()
        return result
    }

    fun encodeFile(prescription: MultipartFile): String {
        return try {
            Base64.getEncoder().encodeToString(prescription.bytes)
        } catch (e: IOException) {
            throw JphBizException(ErrorCode.ENCODE_FILE_ERROR)
        }
    }
}