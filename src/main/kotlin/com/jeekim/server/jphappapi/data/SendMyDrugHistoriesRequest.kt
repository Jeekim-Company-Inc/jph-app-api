package com.jeekim.server.jphappapi.data

import com.jeekim.server.jphappapi.client.kims.data.KimsDrugHistorySendRequest
import com.jeekim.server.jphappapi.model.KimsInputType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "KIMS 약 처방 내역 전송 요청")
data class SendMyDrugHistoriesRequest (
    @Schema(description = "사용자 정보")
    val userInfo: UserInfo,
    @Schema(description = "처방 이력")
    val drugHistories: List<MyDrugHistory>
){
    fun toCommandList(hospitalId: String): KimsDrugHistorySendRequest {
        return KimsDrugHistorySendRequest.of(
            userInfo = userInfo,
            drugHistory = drugHistories,
            hospitalId = hospitalId
        )
    }
}