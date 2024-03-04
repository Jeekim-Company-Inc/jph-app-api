package com.jeekim.server.jphappapi.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "hospital")
data class Hospital(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,
    val name: String,
    val code: Int,
)