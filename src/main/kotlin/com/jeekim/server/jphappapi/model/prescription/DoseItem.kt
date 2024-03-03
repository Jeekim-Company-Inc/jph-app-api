package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.utils.getString

open class DoseItem(
    @JsonProperty("value")
    var value: String,
    @JsonProperty("needCheck")
    var needCheck: Boolean
) {
    constructor() : this("1", true)
    open fun initialize(value: Any?, needCheck: Boolean) {
        val valueString =  value.getString()
        if(value == null || valueString!!.toDouble() == 0.0) {
            this.value = "1"
            this.needCheck = true
            return
        }
        this.value = valueString
        this.needCheck = needCheck
    }
}