package com.jeekim.server.jphappapi.client.kims.data

import com.jeekim.server.jphappapi.data.MyDrugHistory
import com.jeekim.server.jphappapi.data.OcrCheckedRequest
import com.jeekim.server.jphappapi.data.UserInfo
import com.jeekim.server.jphappapi.model.KimsInputType
import com.jeekim.server.jphappapi.model.prescription.PrescriptionContent

data class KimsDrugHistorySendRequest (
    val custID: String,
    val dataType: Int,
    val rxData: RxData
){
    data class RxData(
        val patientNo: String,
        val patientName: String,
        val prescriptions: List<RxPrescription>
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
        val quantity: Double,
        val frequency: Int,
        val dayCount: Int
    ){
        companion object {
            fun ofApi(drug: MyDrugHistory.Drug): RxDrug {
                return RxDrug(
                    rxType = -1,
                    drugCode = drug.medicineCode,
                    drugName = drug.medicine,
                    quantity = drug.oneDose.toDouble(),
                    frequency = drug.numberDoseDay.toInt(),
                    dayCount = drug.totalDoseDay.toInt()
                )
            }

            fun ofInternal(drug: OcrCheckedRequest.PrescriptionContentRequest): RxDrug{
                return RxDrug(
                    rxType = 0,
                    drugCode = checkNotNull(drug.drugCode),
                    drugName = checkNotNull(drug.drugName),
                    quantity = drug.oneDose.toDouble(),
                    frequency = drug.dosingPerDay.toInt(),
                    dayCount = drug.totalDosingDays.toInt()
                )
            }
            fun ofInjection(drug: OcrCheckedRequest.PrescriptionContentRequest): RxDrug{
                return RxDrug(
                    rxType = 2,
                    drugCode = checkNotNull(drug.drugCode),
                    drugName = checkNotNull(drug.drugName),
                    quantity = drug.oneDose.toDouble(),
                    frequency = drug.dosingPerDay.toInt(),
                    dayCount = drug.totalDosingDays.toInt()
                )
            }
        }
    }
    companion object {
        fun of(userInfo: UserInfo, drugHistory: List<MyDrugHistory>, hospitalId: String): KimsDrugHistorySendRequest {
            return KimsDrugHistorySendRequest(
                custID = hospitalId,
                dataType = KimsInputType.API.ordinal,
                rxData = RxData(
                    patientNo = userInfo.createRrn(),
                    patientName = userInfo.name,
                    prescriptions = drugHistory.map {
                        RxPrescription(
                            hospNum = "",
                            hospName = it.hospital,
                            hospDate = it.createdDate,
                            pharmaDate = it.createdDate,
                            drugs = it.list.map {
                                drug -> RxDrug.ofApi(drug)
                            }
                        )
                    }
                )
            )
        }
    }
}