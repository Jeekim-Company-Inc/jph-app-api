package com.jeekim.server.jphappapi.entity

import com.jeekim.server.jphappapi.model.ShootStatus
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "prescription")
data class Prescription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "hospital_id")
    val hospitalId: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: ShootStatus = ShootStatus.WAITING,
    var message: String = "",
): BaseTimeEntity(){
    fun success() {
        status = ShootStatus.SUCCESS
    }
    fun fail(message: String) {
        status = ShootStatus.FAILED
        this.message = message
    }
}