package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.utils.clearValueString
import com.jeekim.server.jphappapi.utils.getString

class PatientCategory : SingleItem() {
    override fun initialize(value: Any, needCheck: Boolean) {
        val valueString = value.getString().clearValueString()
        val type = PatientCategoryType.of(valueString)
        this.value = type.word
        this.needCheck = needCheck
    }
}