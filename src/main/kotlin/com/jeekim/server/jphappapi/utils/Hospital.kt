package com.jeekim.server.jphappapi.utils

object Hospital {

    data class HospitalInfo(
        val id: String,
        val code: String,
        val key: String
    )

    val HOSPITAL = listOf<HospitalInfo>(
        HospitalInfo("test", "12345", "PAKUAS"),
        HospitalInfo("PAKUMC001", "90906", "PAKUMC"),
        HospitalInfo("PAKUMC002", "23450", "PAKUMC"),
        HospitalInfo("PAKUMC003", "06490", "PAKUMC"),
        HospitalInfo("PAKUMC004", "18409", "PAKUMC"),
        HospitalInfo("PAKUMC005", "12666", "PAKUMC"),
        HospitalInfo("PAKUMC006", "41805", "PAKUMC"),
        HospitalInfo("PAKUMC007", "20599", "PAKUMC"),
        HospitalInfo("PAKUMC008", "48447", "PAKUMC"),
        HospitalInfo("PAKUMC009", "53358", "PAKUMC"),
        HospitalInfo("PAKUMC010", "32502", "PAKUMC"),
        HospitalInfo("PAKUMC011", "43599", "PAKUMC"),
        HospitalInfo("PAKUMC012", "55166", "PAKUMC"),
        HospitalInfo("PAKUMC013", "60239", "PAKUMC"),
        HospitalInfo("PAKUMC014", "74596", "PAKUMC"),
        HospitalInfo("PAKUMC015", "64354", "PAKUMC"),
        HospitalInfo("PAKUMC016", "81716", "PAKUMC"),
        HospitalInfo("PAKUMC017", "72538", "PAKUMC"),
        HospitalInfo("PAKUMC018", "85005", "PAKUMC"),
        HospitalInfo("PAKUMC019", "38268", "PAKUMC"),
        HospitalInfo("PAKUMC020", "00375", "PAKUMC"),
        HospitalInfo("PAKUAS001", "30080", "PAKUAS"),
        HospitalInfo("PAKUAS002", "06778", "PAKUAS"),
        HospitalInfo("PAKUAS003", "47635", "PAKUAS"),
        HospitalInfo("PAKUAS004", "88827", "PAKUAS"),
        HospitalInfo("PAKUAS005", "43554", "PAKUAS"),
        HospitalInfo("PAKUAS006", "19998", "PAKUAS"),
        HospitalInfo("PAKUAS007", "70232", "PAKUAS"),
        HospitalInfo("PAKUAS008", "18710", "PAKUAS"),
        HospitalInfo("PAKUAS009", "41488", "PAKUAS"),
        HospitalInfo("PAKUAS010", "08667", "PAKUAS"),
        HospitalInfo("PAKUAS011", "73780", "PAKUAS"),
        HospitalInfo("PAKUAS012", "08627", "PAKUAS"),
        HospitalInfo("PAKUAS013", "27082", "PAKUAS"),
        HospitalInfo("PAKUAS014", "41637", "PAKUAS"),
        HospitalInfo("PAKUAS015", "69306", "PAKUAS"),
        HospitalInfo("PAKUAS016", "94101", "PAKUAS"),
        HospitalInfo("PAKUAS017", "39665", "PAKUAS"),
        HospitalInfo("PAKUAS018", "98464", "PAKUAS"),
        HospitalInfo("PAKUAS019", "05319", "PAKUAS"),
        HospitalInfo("PAKUAS020", "05339", "PAKUAS"),
        HospitalInfo("PAKUGU001", "36353", "PAKUGU"),
        HospitalInfo("PAKUGU002", "29804", "PAKUGU"),
        HospitalInfo("PAKUGU003", "35156", "PAKUGU"),
        HospitalInfo("PAKUGU004", "63617", "PAKUGU"),
        HospitalInfo("PAKUGU005", "80374", "PAKUGU"),
        HospitalInfo("PAKUGU006", "74318", "PAKUGU"),
        HospitalInfo("PAKUGU007", "70493", "PAKUGU"),
        HospitalInfo("PAKUGU008", "04594", "PAKUGU"),
        HospitalInfo("PAKUGU009", "67719", "PAKUGU"),
        HospitalInfo("PAKUGU010", "65053", "PAKUGU"),
        HospitalInfo("PAKUGU011", "12112", "PAKUGU"),
        HospitalInfo("PAKUGU012", "78568", "PAKUGU"),
        HospitalInfo("PAKUGU013", "35426", "PAKUGU"),
        HospitalInfo("PAKUGU014", "93511", "PAKUGU"),
        HospitalInfo("PAKUGU015", "78621", "PAKUGU"),
        HospitalInfo("PAKUGU016", "26634", "PAKUGU"),
        HospitalInfo("PAKUGU017", "17860", "PAKUGU"),
        HospitalInfo("PAKUGU018", "68207", "PAKUGU"),
        HospitalInfo("PAKUGU019", "85026", "PAKUGU"),
        HospitalInfo("PAKUGU020", "14588", "PAKUGU"),
    )
}
