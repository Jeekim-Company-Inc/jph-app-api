package com.jeekim.server.jphappapi.client.infotech.data

import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoRequest
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesByKakaoVerifyRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

data class InfotechEasyVerifyRequest (
    val reqCd: String = UUID.randomUUID().toString(),
    val affix: String,
    val appCd: String = "jeekim",
    val orgCd: String = "hira",
    val svcCd: String = "B0001",
    val loginMethod: String = "EASY",
    val step: String,
    val name: String,
    val mobileNo: String,
    val ssn1: String,
    val ssn2: String,
    val sdate: String,
    val edate: String,
    val step_data: String
){
    companion object{
        fun of(request: GetMyDrugHistoriesByKakaoVerifyRequest): InfotechEasyVerifyRequest{
            val now = LocalDate.now()
            return InfotechEasyVerifyRequest(
                affix = request.phoneNumber,
                step = request.channel,
                name = request.name,
                mobileNo = request.phoneNumber,
                ssn1 = request.rrnFirst,
                ssn2 = request.rrnSecond,
                sdate = now.minusYears(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                edate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                step_data = request.stepInfo
            )
        }
    }
}
