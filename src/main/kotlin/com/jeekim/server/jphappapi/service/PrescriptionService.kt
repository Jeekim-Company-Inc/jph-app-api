package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.client.kims.KimsAdapter
import com.jeekim.server.jphappapi.client.lomin.LominAdapter
import com.jeekim.server.jphappapi.data.OcrResponse
import com.jeekim.server.jphappapi.data.OcrRequest
import com.jeekim.server.jphappapi.entity.Prescription
import com.jeekim.server.jphappapi.repository.PrescriptionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PrescriptionService(
    private val lominAdapter: LominAdapter,
    private val prescriptionRepository: PrescriptionRepository
) {
    @Transactional
    fun postOcr(request: OcrRequest, hospitalId: String): OcrResponse? {
        val prescription = prescriptionRepository.save(Prescription(hospitalId = hospitalId))
        val result = runCatching {
            return lominAdapter.postOcr(request.toCommand()).toOcrResponse()
        }.onFailure {
            prescription.fail(it.message ?: "OCR 실패")
        }.onSuccess {
            prescription.success()
        }.getOrNull()
        return result
    }
}