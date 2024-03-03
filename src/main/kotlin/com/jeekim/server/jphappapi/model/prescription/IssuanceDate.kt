package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.utils.clearValueString
import com.jeekim.server.jphappapi.utils.getString
import com.jeekim.server.jphappapi.utils.toGeneratedDate

class IssuanceDate : SingleItem() {
    override fun initialize(value: Any, needCheck: Boolean) {
        val generatedDate = value.getString().toGeneratedDate()
        this.value = generatedDate.date
        this.needCheck = generatedDate.isGenerated || needCheck
    }
}