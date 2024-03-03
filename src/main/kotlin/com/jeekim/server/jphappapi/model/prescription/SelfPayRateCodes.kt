package com.jeekim.server.jphappapi.model.prescription

import com.jeekim.server.jphappapi.utils.Constants


enum class SelfPayRateCodes(
    val word: String,
    val pmsCode: String
) {
    REIMBURSEMENT("급여", "1"),
    NON_REIMBURSEMENT("비급여", "2"),
    SELF_PAY_100("100% 본인", "3"),
    SELF_PAY_90("90% 본인", "7"),
    SELF_PAY_80("80% 본인", "4"),
    SELF_PAY_60("60% 본인", "8"),
    SELF_PAY_50("50% 본인", "5"),
    SELF_PAY_30("30% 본인", "6"),
    NULL("null", "");

    companion object {
        fun fromInference(valueString: String?): String? {
            if (valueString == null) {
                return null
            }
            for (key in Constants.SELF_PAY_RATE_KEYWORDS.keys) {
                val possibles = Constants.SELF_PAY_RATE_KEYWORDS_WITHOUT_WHITESPACE[key] as Set<String>
                if (possibles.contains(valueString)) {
                    return key
                }
            }
            return null
        }

        fun fromWord(selfPayRateCode: String): SelfPayRateCodes {
            return entries.firstOrNull { it.word == selfPayRateCode } ?: NULL
        }
    }
}

