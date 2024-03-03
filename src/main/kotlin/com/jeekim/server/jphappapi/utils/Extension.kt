package com.jeekim.server.jphappapi.utils

import com.jeekim.server.jphappapi.model.prescription.GeneratedDate
import com.jeekim.server.jphappapi.utils.Constants.Companion.BRACKETS_SET
import java.security.MessageDigest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern

fun Any?.getString(): String? {
    return this?.let{ it.toString().trim().ifBlank { null } }
}

fun Any?.getStringList(): List<String> {
    return try {
        val valuesObjectList = (this as? List<String>) ?: return emptyList()
        valuesObjectList.flatMap { it.trim().removeSurrounding("[", "]").split(",") }
            .mapNotNull { it.trim().takeIf { value -> value.isNotBlank() } }
    } catch (e: Exception) {
        emptyList()
    }
}

fun String?.clearValueString(): String {
    return this?.trim()?.replace(" ", "") ?: ""
}

fun String?.truncateValueString(): String? {
    return this?.substringBefore("(")?.trim()
}

fun String.clearBrackets(): String {
    val containingBrackets: List<String> = BRACKETS_SET.filter { this.contains(it) }
    containingBrackets.forEach { this.replace(it, "") }
    return this
}

fun String.deletePattern(delete: String): String {
    return this.replaceFirst(
        Pattern.quote(delete),
        Matcher.quoteReplacement("")
    ).trim()
}

fun LocalDate.toDateFormatString(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyyMMdd"))
}

fun String?.toGeneratedDate(): GeneratedDate {
    val today = LocalDate.now()
    val todayString = today.toDateFormatString()
    return this?.let{
        runCatching {
            val valueDate = it.toLocalDate()
            if(!valueDate.isAfter(today)) GeneratedDate.ofFalse(valueDate.toDateFormatString())
            else GeneratedDate.ofTrue(todayString)
        }.getOrDefault(GeneratedDate.ofTrue(todayString))
    } ?: GeneratedDate.ofTrue(todayString)
}

fun String.hashSHA512(): String {
    val bytes = this.toByteArray()
    val messageDigest = MessageDigest.getInstance("SHA-512")
    val hashedBytes = messageDigest.digest(bytes)
    return hashedBytes.joinToString("") { "%02x".format(it) }
}