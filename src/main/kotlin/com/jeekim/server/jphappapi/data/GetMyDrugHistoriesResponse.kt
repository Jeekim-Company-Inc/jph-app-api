package com.jeekim.server.jphappapi.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "내 약 조회 응답")
data class GetMyDrugHistoriesResponse (
    @Schema(description = "사용자 정보")
    val userInfo: UserInfo,
    @Schema(description = "처방 이력")
    val drugHistories: List<MyDrugHistoryResponse>
){

}