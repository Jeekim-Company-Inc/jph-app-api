package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.utils.getStringList

open class CompositeItem(
    @JsonProperty("values")
    var values: List<String>,
    @JsonProperty("needCheck")
    var needCheck: Boolean,
) {
    constructor() : this(emptyList(), true)
    open fun initialize(values: Any?, needCheck: Boolean) {
        this.values = values.getStringList()
        this.needCheck = needCheck
    }
}