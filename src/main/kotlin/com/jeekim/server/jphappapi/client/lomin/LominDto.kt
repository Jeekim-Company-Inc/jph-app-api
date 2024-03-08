package com.jeekim.server.jphappapi.client.lomin

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.client.lomin.model.PrescriptionCode
import com.jeekim.server.jphappapi.model.prescription.Bbox


data class Prediction(
    @JsonProperty("key_values") val keyValues: List<KeyValue>,
    @JsonProperty("tables") val tables: List<Table>
)

data class KeyValue(
    val key: String,
    val confidence: Any,
    val value: Any
)

data class Table(
    @JsonProperty("column-header") val columnHeader: ColumnHeader,
    @JsonProperty("body") val tableBody: TableBody,
    @JsonProperty("key") val tableKey: String,
) {
    fun isInternalTable(): Boolean {
        return tableKey == PrescriptionCode.INTERNAL_PRESCRIPTION_TABLE.code
    }
}

data class ColumnHeader(
    @JsonProperty("key_code") val keys: List<String>?
)

data class TableBody(
    @JsonProperty("content") val contents: List<List<String>>?,
    @JsonProperty("score") val scores: List<List<Double>>?
)

data class PostInferenceRequest(
    var document: Document? = null,
    val rectify: Rectify = Rectify(),
    val hint: Hint = Hint(),
    @JsonProperty("detection_score_threshold") val detectionScoreThreshold: Double = 0.5,
    @JsonProperty("detection_resize_ratio") val detectionResizeRatio: Double = 0.5,
    @JsonProperty("idcard_version") val idCardVersion: String = "v1",
    @JsonProperty("page") val page: Int = 1,
)

data class Document(
    val file: String,
    @JsonProperty("file_path") val filePath: String
)

data class Rectify(
    @JsonProperty("rotation_90n") val rotation90n: Boolean = true,
    @JsonProperty("rotation_fine") val rotationFine: Boolean = true
)

data class Hint(
    @JsonProperty("doc_type") val docType: DocType = DocType(),
    @JsonProperty("key_value") val keyValue: List<Any> = emptyList(),
)

data class DocType(
    val use: Boolean = true,
    val trust: Boolean = true,
    @JsonProperty("doc_type") val docType: String = "MD-PRS"
)