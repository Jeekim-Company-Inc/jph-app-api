package com.jeekim.server.jphappapi.model

enum class SmsType(val step: String) {
    SEND("captcha"), REFRESH("captchaRefresh"), LOGIN("identityCheck");
}