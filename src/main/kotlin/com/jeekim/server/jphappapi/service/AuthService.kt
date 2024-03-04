package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.exception.AuthException
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.repository.HospitalRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val hospitalRepository: HospitalRepository
) {

    fun existsHospital(id: String){
        if(!hospitalRepository.existsById(id)){
            throw AuthException(ErrorCode.HOSPITAL_NOT_FOUND)
        }
    }
}