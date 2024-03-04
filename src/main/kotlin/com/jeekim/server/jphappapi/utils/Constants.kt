package com.jeekim.server.jphappapi.utils

import com.jeekim.server.jphappapi.model.prescription.SelfPayRateCodes

class Constants {
    companion object {
        const val NEED_CHECK_CONFIDENCE_THRESHOLD = 0.6
        val BRACKETS_SET: Set<String> = setOf( "(", ")", "[", "]", "{", "}", "<", ">" )
        val SELF_PAY_RATE_KEYWORDS = mapOf(
            SelfPayRateCodes.REIMBURSEMENT.word to setOf(
                "급여", "[급여]", "급여)", "(급여)", "(급)", "[급]", "급 ", "급)", "보 ", "보험)", "(보험)", "보험", "[보험]"
            ),
            SelfPayRateCodes.NON_REIMBURSEMENT.word to setOf(
                "비)", "(비)", "비 ", "[비]", "비보", "비보)", "(비보)", "[비보]", "비보험)", "(비보험)", "비보험", "[비보험]",
                "비급여)", "(비급여)", "비급여", "[비급여]", "비급)", "[비급]", "(비급)", "비급", "W ", "(W)", "W)", "[W]", "W(비급)",
                "W(비급여)", "W(비보)", "W(비보험)", "W(비)"
            ),
            SelfPayRateCodes.SELF_PAY_100.word to setOf(
                "본)", "(본)", "본 ", "[본]", "100/100", "(100/100)", "100/100)", "[100/100]", "100%", "(100%)", "100%)",
                "[100%]", "U ", "(U)", "U)", "[U]", "V ", "(V)", "V)", "[V]", "V(본)", "V(본인)", "V(본인부담)",
                "U(본)", "U(본인)", "U(본인부담)", "본인부담)", "(본인부담)", "[본인부담]", "본인부담", "[본인]"
            ),
            SelfPayRateCodes.SELF_PAY_80.word to setOf(
                "80/100", "(80/100)", "80/100)", "[80/100]", "B ", "(B)", "B)", "[B]"
            ),
            SelfPayRateCodes.SELF_PAY_50.word to setOf(
                "50/100", "(50/100)", "50/100)", "[50/100]", "A ", "(A)", "A)", "[A]"
            ),
            SelfPayRateCodes.SELF_PAY_30.word to setOf("D ", "(D)", "D)", "[D]")
        )
        val SELF_PAY_RATE_KEYWORDS_WITHOUT_WHITESPACE = mapOf(
            SelfPayRateCodes.REIMBURSEMENT.word to setOf(
                "급여", "[급여]", "급여)", "(급여)", "(급)", "[급]", "급", "급)", "보", "보험)", "(보험)", "보험", "[보험]"
            ),
            SelfPayRateCodes.NON_REIMBURSEMENT.word to setOf(
                "비)", "(비)", "비", "[비]", "비보", "비보)", "(비보)", "[비보]",
                "비보험)", "(비보험)", "비보험", "[비보험]", "비급여)", "(비급여)", "비급여", "[비급여]",
                "비급)", "[비급]", "(비급)", "비급", "W", "(W)", "W)", "[W]", "W(비급)", "W(비급여)", "W(비보)", "W(비보험)", "W(비)"
            ),
            SelfPayRateCodes.SELF_PAY_100.word to setOf(
                "본)", "(본)", "본", "[본]", "100/100", "(100/100)", "100/100)", "[100/100]",
                "100%", "(100%)", "100%)", "[100%]", "U", "(U)", "U)", "[U]", "V", "(V)", "V)", "[V]",
                "V(본)", "V(본인)", "V(본인부담)", "U(본)", "U(본인)", "U(본인부담)", "본인부담)", "(본인부담)", "[본인부담]", "본인부담"
            ),
            SelfPayRateCodes.SELF_PAY_80.word to setOf(
                "80/100", "(80/100)", "80/100)", "[80/100]", "B", "(B)", "B)", "[B]"
            ),
            SelfPayRateCodes.SELF_PAY_50.word to setOf(
                "50/100", "(50/100)", "50/100)", "[50/100]", "A", "(A)", "A)", "[A]"
            ),
            SelfPayRateCodes.SELF_PAY_30.word to setOf(
                "D", "(D)", "D)", "[D]"
            )
        )

    }
}
