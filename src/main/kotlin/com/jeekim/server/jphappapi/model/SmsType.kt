package com.jeekim.server.jphappapi.model

enum class SmsType(val step: String) {
    SEND("captchaPass"), LOGIN("identityCheck");
}