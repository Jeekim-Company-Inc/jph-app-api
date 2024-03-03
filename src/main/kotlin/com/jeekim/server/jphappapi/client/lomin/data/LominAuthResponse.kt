package com.jeekim.server.jphappapi.client.lomin.data

import com.fasterxml.jackson.annotation.JsonProperty

data class LominAuthResponse (
    @JsonProperty("access_token")
    val accessToken: String
)