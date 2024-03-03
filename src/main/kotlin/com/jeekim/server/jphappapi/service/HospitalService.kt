package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.repository.HospitalRepository
import com.jeekim.server.jphappapi.utils.JwtUtils
import org.bouncycastle.asn1.x500.style.RFC4519Style.name
import org.springframework.stereotype.Service

@Service
class HospitalService(
    private val hospitalRepository: HospitalRepository,
) {
    fun login(
        id: String,
        code: Int
    ) {
        hospitalRepository.findByIdAndCode(id, code) ?: throw JphBizException(ErrorCode.HOSPITAL_CODE_NOT_MATCH)
    }
}