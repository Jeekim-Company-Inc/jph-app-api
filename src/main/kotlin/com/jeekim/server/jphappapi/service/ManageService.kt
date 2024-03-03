package com.jeekim.server.jphappapi.service

import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.repository.ManageRepository
import org.springframework.stereotype.Service

@Service
class ManageService(
    private val manageRepository: ManageRepository
) {
    fun isAllowed(){
        val isAllowed = manageRepository.findFirstBy()?.isAllowed ?: throw JphBizException(ErrorCode.HOSPITAL_NOT_ALLOWED)
        if(!isAllowed){
            throw JphBizException(ErrorCode.HOSPITAL_NOT_ALLOWED)
        }
    }
}