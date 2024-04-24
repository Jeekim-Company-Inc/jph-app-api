package com.jeekim.server.jphappapi.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "간편 인증 응답")
data class GetEasyLoginResponse (
    @Schema(description = "진행 스텝 정보, (참고) /drug/easy/send API에 포함해서 응답해야 함")
    val stepInfo: String
)