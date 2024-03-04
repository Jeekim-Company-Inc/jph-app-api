package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.utils.clearValueString
import com.jeekim.server.jphappapi.utils.getString
import com.jeekim.server.jphappapi.utils.truncateValueString

class PatientName : SingleBboxItem() {
    override fun initialize(value: Any, needCheck: Boolean, bbox: Bbox) {
        val valueString = value.getString().truncateValueString()
        this.value = valueString
        this.needCheck = needCheck
        this.bbox = bbox
    }
}