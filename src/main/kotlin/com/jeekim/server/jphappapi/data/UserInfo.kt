package com.jeekim.server.jphappapi.data

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UserInfo(
    @Schema(description = "주민등록번호 앞자리")
    @field:Pattern(regexp = "^([0-9]{2})((0[1-9])|(1[0-2]))((0[1-9])|([12][0-9])|(3[01]))$", message = "주민등록번호 앞자리는 유효한 날짜를 나타내야 합니다.")
    val rrnFirst: String = "",

    @Schema(description = "주민등록번호 뒷자리")
    @field:Pattern(regexp = "^[0-9]{7}$", message = "주민등록번호 뒷자리는 7자리 숫자입니다.")
    val rrnSecond: String = "",
    @Schema(description = "이름")
    @field:NotBlank
    val name: String = "",
){
    fun createRrn(): String = "$rrnFirst-$rrnSecond"
}
