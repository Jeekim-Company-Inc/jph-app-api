package com.jeekim.server.jphappapi.client.infotech.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesVerifyResponse
import com.jeekim.server.jphappapi.data.MyDrugHistoryResponse
import com.jeekim.server.jphappapi.data.UserInfo
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException

data class InfotechEasyResponse (
    @JsonProperty("resCd")
    val resCd: String,
    @JsonProperty("resMsg")
    val resMsg: String,
    @JsonProperty("out")
    val out: OutData,
){
    data class OutData(
        @JsonProperty("errYn")
        val errYn: String,
        @JsonProperty("errMsg")
        val errMsg: String,
        @JsonProperty("step_data")
        val step_data: String
    )
}