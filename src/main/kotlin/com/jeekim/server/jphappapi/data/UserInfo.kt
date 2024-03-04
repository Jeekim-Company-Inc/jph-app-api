package com.jeekim.server.jphappapi.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema

data class UserInfo(
    @Schema(description = "주민등록번호 앞자리")
    @JsonIgnoreProperties("rrnFirst")
    val rrnFirst: String = "",
    @Schema(description = "주민등록번호 뒷자리")
    @JsonIgnoreProperties("rrnSecond")
    val rrnSecond: String = "",
    @Schema(description = "이름")
    @JsonIgnoreProperties("name")
    val name: String = "",
){
    fun createRrn(): String = "$rrnFirst-$rrnSecond"
}
