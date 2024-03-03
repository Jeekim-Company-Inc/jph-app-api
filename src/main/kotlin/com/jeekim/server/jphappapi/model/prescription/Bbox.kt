package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty

data class Bbox(
    @JsonProperty("x")
    val x: Int = 0,
    @JsonProperty("y")
    val y: Int = 0,
    @JsonProperty("w")
    val width: Int = 0,
    @JsonProperty("h")
    val height: Int = 0,
)
