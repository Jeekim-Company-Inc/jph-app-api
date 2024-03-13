package com.jeekim.server.jphappapi.model

enum class SmsType(val step: String) {
    SEND("captcha"), LOGIN("identityCheck");
}