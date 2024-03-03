package com.jeekim.server.jphappapi.repository

import com.jeekim.server.jphappapi.entity.Hospital
import org.springframework.data.jpa.repository.JpaRepository

interface HospitalRepository: JpaRepository<Hospital, String> {
    fun findByIdAndCode(id: String, code: Int): Hospital?
}