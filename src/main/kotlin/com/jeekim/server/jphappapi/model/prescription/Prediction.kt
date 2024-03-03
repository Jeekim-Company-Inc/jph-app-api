package com.jeekim.server.jphappapi.model.prescription

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class Prediction(
    @JsonProperty("key_values")
    val keyValueList: List<KeyValue>,
    @JsonProperty("texts")
    val ocrTextList: List<OcrText>,
    @JsonProperty("tables")
    val tableList: List<Table>
){
    data class KeyValue(
        @JsonProperty("key")
        val key: String,
        @JsonProperty("confidence")
        val confidence: Any,
        @JsonProperty("value")
        val value: Any,
        @JsonProperty("bbox")
        val bbox: Bbox
    )

    data class OcrText(
        @JsonProperty("text")
        val text: String,
        @JsonProperty("kv_ids")
        val keyValueIds: List<String>,
        @JsonProperty("bbox")
        val bbox: Bbox
    ){
        fun isNotMapped(): Boolean {
            return keyValueIds.isNotEmpty() && keyValueIds[0] == "NONE"
        }
    }

    data class Table(
        @JsonProperty("column-header")
        val columnHeader: ColumnHeader,
        @JsonProperty("body")
        val body: TableBody,
        @JsonProperty("key")
        val tableKey: String
    ){
        data class TableBody(
            @JsonProperty("content")
            val content: List<List<String>> = emptyList(),
            @JsonProperty("score")
            val scores: List<List<Double>> = emptyList()
        )

        data class ColumnHeader(
            @JsonProperty("key_code")
            val keys: List<String> = emptyList()
        )

        fun isInternalTable(): Boolean {
            return tableKey == PrescriptionCode.INTERNAL_PRESCRIPTION_TABLE.code
        }
    }
}