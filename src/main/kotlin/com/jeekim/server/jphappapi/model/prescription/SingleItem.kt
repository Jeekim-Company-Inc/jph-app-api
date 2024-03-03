package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.utils.getString

open class SingleItem(
    @JsonProperty("value")
    var value: String?,
    @JsonProperty("needCheck")
    var needCheck: Boolean
) {
    constructor() : this(null, true)

    open fun initialize(value: Any, needCheck: Boolean) {
        this.value = value.getString()
        this.needCheck = needCheck
    }
}