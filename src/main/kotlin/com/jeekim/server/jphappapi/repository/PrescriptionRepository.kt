package com.jeekim.server.jphappapi.repository

import com.jeekim.server.jphappapi.entity.Prescription
import org.springframework.data.jpa.repository.JpaRepository

interface PrescriptionRepository: JpaRepository<Prescription, Long> {
}