package com.jeekim.server.jphappapi.client.infotech.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesResponse
import com.jeekim.server.jphappapi.data.MyDrugHistory
import com.jeekim.server.jphappapi.data.UserInfo
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.JphBizException

data class InfotechMyDrugHistoriesResponse (
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
        @JsonProperty("outB0001")
        val outB0001: OutB0001 = OutB0001()
    ){
        data class OutB0001(
            @JsonProperty("errYn")
            val errYn: String = "N",
            @JsonProperty("errMsg")
            val errMsg: String = "",
            @JsonProperty("list")
            val list: List<MyDrugHistory> = emptyList()
        )
    }

    fun toResponse(userInfo: UserInfo): GetMyDrugHistoriesResponse{
        if(resCd != "0000") throw JphBizException(ErrorCode.INFOTECH_API_ERROR, resMsg)
        if(out.errYn == "Y") throw JphBizException(ErrorCode.INFOTECH_API_ERROR, out.errMsg)
        if(out.outB0001.errYn == "Y") throw JphBizException(ErrorCode.INFOTECH_API_ERROR, out.outB0001.errMsg)

        return GetMyDrugHistoriesResponse(
            userInfo = userInfo,
            drugHistories = out.outB0001.list
        )
    }
}