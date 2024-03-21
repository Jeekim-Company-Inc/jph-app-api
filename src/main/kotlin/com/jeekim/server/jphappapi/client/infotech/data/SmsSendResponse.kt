package com.jeekim.server.jphappapi.client.infotech.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "내 약 조회 응답-SMS 전송")
data class SmsSendResponse (
    val resCd: String,
    val resMsg: String,
    val out: Out
){
    data class Out(
        val userData: String = "",
        val captcha_img: String = "",
        val errYn: String,
    )
}
