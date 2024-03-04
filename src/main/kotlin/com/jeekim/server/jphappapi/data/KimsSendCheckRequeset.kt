package com.jeekim.server.jphappapi.data

data class KimsSendCheckRequeset (
    val rrnFirst: String,
    val rrnSecond: String,
){
    fun createRrn(): String {
        return "$rrnFirst-$rrnSecond"
    }
}