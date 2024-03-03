package com.jeekim.server.jphappapi.model.prescription

import com.jeekim.server.jphappapi.utils.getString
import com.jeekim.server.jphappapi.utils.logger

class SelfPayRateCode : SingleItem() {
    override fun initialize(value: Any, needCheck: Boolean) {
        val valueString = value.getString()
        val code = SelfPayRateCodes.fromInference(valueString)
        this.value = code
        this.needCheck = needCheck
    }
}