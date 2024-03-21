package com.jeekim.server.jphappapi.data

import io.swagger.v3.oas.annotations.media.Schema
import org.jetbrains.annotations.NotNull

@Schema(description = "약 처방 내역")
data class MyDrugHistoryRequest (
    @Schema(description = "순번")
    @NotNull
    val no: String,
    @Schema(description = "처방일")
    @NotNull
    val createdDate: String,
    @Schema(description = "처방 병원")
    @NotNull
    val hospital: String,
    @Schema(description = "처방 약국")
    @NotNull
    val drugStore: String,
    @Schema(description = "약 목록")
    @NotNull
    val list: List<Drug>,
){
    @Schema(description = "약 정보")
    data class Drug(
        @Schema(description = "순번")
        @NotNull
        val no : String,
        @Schema(description = "약 이름")
        @NotNull
        val medicine: String?,
        @Schema(description = "약 효능")
        @NotNull
        val medicinalEffect: String,
        @Schema(description = "약 성분")
        @NotNull
        val ingredient: String,
        @Schema(description = "약 코드")
        @NotNull
        val medicineCode: String,
        @Schema(description = "약 단위")
        @NotNull
        val unit: String,
        @Schema(description = "약 용량")
        @NotNull
        val oneDose: String,
        @Schema(description = "약 복용 주기")
        @NotNull
        val numberDoseDay: String,
        @Schema(description = "약 총 복용 일수")
        @NotNull
        val totalDoseDay: String,
        @Schema(description = "안정성?")
        @NotNull
        val stability: String,
    )
}