package com.jeekim.server.jphappapi.data

import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Schema(description = "내 약 조회 요청")
data class GetMyDrugHistoriesByKakaoRequest (
    @field:Schema(description = "이름", required = true)
    @field:NotNull(message = "이름은 필수입니다.")
    val name: String,

    @field:Schema(description = "간편인증 채널", required = true, example = "kakao")
    @field:NotNull(message = "간편인증 채널은 필수입니다.")
    val channel : String,

    @field:Schema(description = "전화번호", required = true, example = "01012345678")
    @field:Pattern(regexp = "^01[0-9]{8,9}$", message = "전화번호는 01로 시작하는 10~11자리 숫자입니다.")
    val phoneNumber: String,

    @field:Schema(description = "주민등록번호 앞자리", required = true, example = "123456")
    @field:Pattern(regexp = "^([0-9]{2})((0[1-9])|(1[0-2]))((0[1-9])|([12][0-9])|(3[01]))$", message = "주민등록번호 앞자리는 유효한 날짜를 나타내야 합니다.")
    val rrnFirst: String,

    @field:Schema(description = "주민등록번호 뒷자리", required = true, example = "1234567")
    @field:Pattern(regexp = "^[0-9]{7}$", message = "주민등록번호 뒷자리는 7자리 숫자입니다.")
    val rrnSecond: String,
){
    fun toUserInfo(): UserInfo = UserInfo(rrnFirst, rrnSecond, name)
}
