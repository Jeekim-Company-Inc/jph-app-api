package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty

data class Bbox (
    @JsonProperty("x")
    val x: Any,
    @JsonProperty("y")
    val y: Any,
    @JsonProperty("w")
    val w: Any,
    @JsonProperty("h")
    val h: Any,
)