package com.jeekim.server.jphappapi.data

import com.jeekim.server.jphappapi.client.lomin.data.LominOcrRequest
import java.util.UUID

data class OcrRequest (
    val extension: String,
    val encodedPrescription: String
){
    fun toCommand(): LominOcrRequest {
        val randomFileName = UUID.randomUUID().toString()
        return LominOcrRequest.of(
            this.encodedPrescription,
            randomFileName + "." + this.extension
        )
    }
}