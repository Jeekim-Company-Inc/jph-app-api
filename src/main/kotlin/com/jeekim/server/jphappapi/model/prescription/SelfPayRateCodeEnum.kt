package com.jeekim.server.jphappapi.model.prescription

import com.jeekim.server.jphappapi.utils.PrescriptionParser

enum class SelfPayRateCodeEnum(
    val word: String
) {
    REIMBURSEMENT("급여"),
    NON_REIMBURSEMENT("비급여"),
    SELF_PAY_100("100% 본인"),
    SELF_PAY_90("90% 본인"),
    SELF_PAY_80("80% 본인"),
    SELF_PAY_60("60% 본인"),
    SELF_PAY_50("50% 본인"),
    SELF_PAY_30("30% 본인"),
    NULL("null");


    companion object {
        fun fromWord(selfPayRateCode: String?): SelfPayRateCodeEnum {
            for (selfPayRateCodes in SelfPayRateCodeEnum.entries) {
                if (selfPayRateCode != null && selfPayRateCodes.word == selfPayRateCode) {
                    return selfPayRateCodes
                }
            }

            return NULL
        }

        fun fromInference(valueString: String?): String? {
            if (valueString == null) {
                return null
            }

            for (key in PrescriptionParser.SELF_PAY_RATE_KEYWORDS.keys) {
                if (PrescriptionParser.SELF_PAY_RATE_KEYWORDS_WITHOUT_WHITESPACE[key]!!.contains(valueString)) {
                    return key
                }
            }

            return null
        }
    }
}