package com.jeekim.server.jphappapi.client.kims.data

import com.jeekim.server.jphappapi.data.MyDrugHistory
import com.jeekim.server.jphappapi.data.UserInfo
import com.jeekim.server.jphappapi.model.KimsInputType
import com.jeekim.server.jphappapi.utils.hashSHA512

data class KimsDrugHistorySendRequest (
    val custID: String,
    val pKey: String,
    val dataType: Int,
    val rxData: RxData
){
    data class RxData(
        val patientNo: String,
        val patientName: String,
        val prescription: RxPrescription
    )
    data class RxPrescription(
        val hospNum: String,
        val hospName: String,
        val hospDate: String,
        val pharmaDate: String,
        val drugs: List<RxDrug>
    )
    data class RxDrug(
        val rxType: Int,
        val drugCode: String,
        val drugName: String,
        val quantity: Int,
        val frequency: Int,
        val dayCount: Int
    )
    companion object {
        fun of(userInfo: UserInfo, drugHistory: MyDrugHistory, hospitalId: String): KimsDrugHistorySendRequest {
            return KimsDrugHistorySendRequest(
                custID = hospitalId,
                pKey = createPKey(userInfo.getRrn()),
                dataType = KimsInputType.API.ordinal,
                rxData = RxData(
                    patientNo = userInfo.getRrn(),
                    patientName = userInfo.name,
                    prescription = RxPrescription(
                        hospNum = hospitalId,
                        hospName = drugHistory.hospital,
                        hospDate = drugHistory.createdDate,
                        pharmaDate = drugHistory.createdDate,
                        drugs = drugHistory.list.map {
                            RxDrug(
                                rxType = -1,
                                drugCode = it.medicineCode,
                                drugName = it.medicine,
                                quantity = it.oneDose.toInt(),
                                frequency = it.numberDoseDay.toInt(),
                                dayCount = it.totalDoseDay.toInt()
                            )
                        }
                    )
                )
            )
        }

        private fun createPKey(rrn: String): String {
            return rrn.hashSHA512().hashSHA512()
        }
    }
}