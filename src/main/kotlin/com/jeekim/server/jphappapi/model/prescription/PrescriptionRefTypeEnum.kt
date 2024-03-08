package com.jeekim.server.jphappapi.model.prescription

enum class PrescriptionRefTypeEnum(
    val word: String?,
    val possibles: Set<String>
) {
    POWDER("가루약", setOf("가루")),
    COVID("코로나", setOf("코로나")),
    NONE(null, setOf());

    companion object {
        fun determineType(valueString: String?): PrescriptionRefTypeEnum {
            if (valueString == null) {
                return NONE
            }

            for (prescriptionRefType in PrescriptionRefTypeEnum.entries) {
                for (word in prescriptionRefType.possibles) {
                    if (valueString.contains(word)) {
                        return prescriptionRefType
                    }
                }
            }

            return NONE
        }
    }
}