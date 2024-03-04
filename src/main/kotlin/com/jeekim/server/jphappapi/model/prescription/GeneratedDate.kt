package com.jeekim.server.jphappapi.model.prescription

data class GeneratedDate(
    val date: String,
    val isGenerated: Boolean
){
    companion object{
        fun ofTrue(date: String): GeneratedDate {
            return GeneratedDate(date, true)
        }
        fun ofFalse(date: String): GeneratedDate {
            return GeneratedDate(date, false)
        }
    }
}
