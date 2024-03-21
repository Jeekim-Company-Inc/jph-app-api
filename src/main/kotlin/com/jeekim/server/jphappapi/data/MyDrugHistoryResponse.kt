package com.jeekim.server.jphappapi.data

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "약 처방 내역")
data class MyDrugHistoryResponse (
    @Schema(description = "순번")
    @JsonProperty("no")
    val no: String,
    @Schema(description = "처방일")
    @JsonProperty("createdDate")
    val createdDate: String,
    @Schema(description = "처방 병원")
    @JsonProperty("hospital")
    val hospital: String,
    @Schema(description = "처방 약국")
    @JsonProperty("drugStore")
    val drugStore: String,
    @Schema(description = "약 목록")
    @JsonProperty("list")
    val list: List<Drug>,
){
    @Schema(description = "약 정보")
    data class Drug(
        @Schema(description = "순번")
        @JsonProperty("no")
        val no : String,
        @Schema(description = "약 이름")
        @JsonProperty("medicine")
        val medicine: String?,
        @Schema(description = "약 효능")
        @JsonProperty("medicinalEffect")
        val medicinalEffect: String,
        @Schema(description = "약 성분")
        @JsonProperty("ingredient")
        val ingredient: String,
        @Schema(description = "약 코드")
        @JsonProperty("medicineCode")
        val medicineCode: String,
        @Schema(description = "약 단위")
        @JsonProperty("unit")
        val unit: String,
        @Schema(description = "약 용량")
        @JsonProperty("oneDose")
        val oneDose: String,
        @Schema(description = "약 복용 주기")
        @JsonProperty("numberDoseDay")
        val numberDoseDay: String,
        @Schema(description = "약 총 복용 일수")
        @JsonProperty("totalDoseDay")
        val totalDoseDay: String,
        @Schema(description = "안정성?")
        @JsonProperty("stability")
        val stability: String,
    )
}