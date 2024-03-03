package com.jeekim.server.jphappapi.model.prescription


enum class PatientCategoryType(val word: String, val pmsCode: String) {
    HEALTH("의료보험", "0"),
    MEDICAL_AID("의료급여", "1"),
    INDUSTRIAL_ACCIDENT("산재보험", "2"),
    CAR("자동차보험", "3"),
    OTHER("기타", "4");

    companion object {
        fun of(word: String): PatientCategoryType {
            return entries.firstOrNull { it.word == word } ?: HEALTH
        }

        fun isNonBenefit(word: String): Boolean {
            return word == CAR.word || word == OTHER.word
        }
    }
}

