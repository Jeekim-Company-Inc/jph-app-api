package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.jeekim.server.jphappapi.utils.getString

open class SingleBboxItem(
    @JsonProperty("value")
    var value: String?,
    @JsonProperty("needCheck")
    var needCheck: Boolean,
    @JsonProperty("bbox")
    var bbox: Bbox
) {
    constructor() : this(null, true, Bbox())
    open fun initialize(value: Any, needCheck: Boolean, bbox: Bbox) {
        this.value = value.getString()
        this.needCheck = needCheck
        this.bbox = bbox
    }
}