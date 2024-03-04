package com.jeekim.server.jphappapi.client.lomin.data

import com.fasterxml.jackson.annotation.JsonProperty

data class LominAuthRequest (
    @JsonProperty("email")
    val email: String,
    @JsonProperty("password")
    val password: String
)