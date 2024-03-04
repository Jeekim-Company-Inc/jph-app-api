package com.jeekim.server.jphappapi.model.prescription


enum class PrescriptionRefType(val word: String?, private val possibles: Set<String>) {
    POWDER("가루약", setOf("가루", "pow", "갈아", "파우더", "POW")),
    COVID("코로나", setOf("코로나", "covid", "COVID")),
    NONE(null, setOf());

    companion object {
        fun of(valueString: String?): PrescriptionRefType {
            return entries.firstOrNull { it.possibles.contains(valueString) } ?: NONE
        }

        fun determineType(valueString: String?): PrescriptionRefType {
            if(valueString == null) return NONE
            return entries.firstOrNull { it.possibles.any { possible -> valueString.contains(possible) } } ?: NONE
        }
    }
}

