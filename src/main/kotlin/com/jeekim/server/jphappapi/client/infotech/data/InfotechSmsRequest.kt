package com.jeekim.server.jphappapi.client.infotech.data

import com.fasterxml.jackson.annotation.JsonInclude
import com.jeekim.server.jphappapi.data.GetMyDrugHistoriesBySmsRequest
import com.jeekim.server.jphappapi.data.SmsSendRequest
import com.jeekim.server.jphappapi.model.SmsType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class InfotechSmsRequest (
    val reqCd: String = UUID.randomUUID().toString(),
    val affix: String,
    val appCd: String = "jeekim",
    val orgCd: String = "hira",
    val svcCd: String = "B0001",
    val loginMethod: String = "SMS",
    val step: String,
    val step_input: String, // 추가
    val mobileCo: String,
    val userData: String?, // 추가
    val name: String,
    val mobileNo: String,
    val ssn1: String,
    val ssn2: String,
    val sdate: String,
    val edate: String,
){
    companion object{
        fun of(request: SmsSendRequest): InfotechSmsRequest{
            val now = LocalDate.now()
            return InfotechSmsRequest(
                affix = request.phoneNumber,
                step = SmsType.SEND.step,
                name = request.name,
                mobileNo = request.phoneNumber,
                ssn1 = request.rrnFirst,
                ssn2 = request.rrnSecond,
                sdate = now.minusYears(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                edate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                step_input = "",
                mobileCo = request.mobileCo.name,
                userData = null
            )
        }
        fun of(request: GetMyDrugHistoriesBySmsRequest): InfotechSmsRequest{
            val now = LocalDate.now()
            return InfotechSmsRequest(
                affix = request.phoneNumber,
                step = SmsType.LOGIN.step,
                name = request.name,
                mobileNo = request.phoneNumber,
                ssn1 = request.rrnFirst,
                ssn2 = request.rrnSecond,
                sdate = now.minusYears(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                edate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                step_input = request.stepInput, // 추가
                mobileCo = request.mobileCo.name, // 추가
                userData = request.userData // 추가
            )
        }
    }
}
