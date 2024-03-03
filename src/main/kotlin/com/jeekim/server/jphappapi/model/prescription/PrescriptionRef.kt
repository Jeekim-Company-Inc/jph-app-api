package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.utils.clearValueString
import com.jeekim.server.jphappapi.utils.getString

class PrescriptionRef : SingleItem() {
    override fun initialize(value: Any, needCheck: Boolean) {
        val valueString = value.getString()
        val type = PrescriptionRefType.determineType(valueString)
        this.value = type.word
        this.needCheck = needCheck
    }
}