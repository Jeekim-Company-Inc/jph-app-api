package com.jeekim.server.jphappapi.repository

import com.jeekim.server.jphappapi.entity.Manage
import org.springframework.data.jpa.repository.JpaRepository

interface ManageRepository: JpaRepository<Manage, Long> {
    fun findFirstBy(): Manage?

}