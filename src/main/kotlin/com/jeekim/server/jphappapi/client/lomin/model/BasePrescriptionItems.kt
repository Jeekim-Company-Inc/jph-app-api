package com.jeekim.server.jphappapi.client.lomin.model

import com.jeekim.server.jphappapi.utils.ParserUtils

interface DosePrescriptionItem {
    var value: String
    var needCheck: Boolean

    fun initialize(value: Any?, needCheck: Boolean) {
        val valueString = ParserUtils.getStringValue(value)
        if(value == null || valueString!!.toDouble() == 0.0) {
            this.value = "1"
            this.needCheck = true
            return
        }

        this.value = valueString
        this.needCheck = needCheck
    }
}

interface SinglePrescriptionItem {
    var value: String?
    var needCheck: Boolean

    fun initialize(value: Any?, needCheck: Boolean) {
        this.value = ParserUtils.getStringValue(value)
        this.needCheck = needCheck
    }
}

interface CompositePrescriptionItem {
    var values: MutableList<String>?
    var needCheck: Boolean

    fun initialize(value: Any?, needCheck: Boolean) {
        this.values = ParserUtils.getStringList(value)
        this.needCheck = needCheck
    }
}
