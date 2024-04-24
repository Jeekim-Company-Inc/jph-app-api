package com.jeekim.server.jphappapi.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val httpStatus: HttpStatus,
    val message: String,
    var userMessage: String? = null
) {
    //인가 관련 에러
    HOSPITAL_CODE_NOT_MATCH("000003", HttpStatus.UNAUTHORIZED, "대상 병원 코드가 일치하지 않음", null),
    HOSPITAL_NOT_FOUND("000005", HttpStatus.UNAUTHORIZED, "대상 병원을 찾을 수 없음", null),
    HEADER_NOT_FOUND("000006", HttpStatus.UNAUTHORIZED, "헤더를 찾을 수 없음", null),


    //비즈니스 에러
    TERMS_NAME_IS_NOT_MATCH("100001", HttpStatus.BAD_REQUEST, "약관 이름이 일치하지 않음", null),
    TERMS_IMAGES_NOT_EXISTS("100002", HttpStatus.BAD_REQUEST, "약관 이미지가 존재하지 않음", null),

    // 외부 호출 에러
    INFOTECH_API_ERROR("200001", HttpStatus.INTERNAL_SERVER_ERROR, "인포텍 API 호출 에러", null),
    KIMS_API_ERROR("200003", HttpStatus.INTERNAL_SERVER_ERROR, "KIMS API 호출 에러", null),
    SMS_CODE_NOT_MATCH("200005", HttpStatus.BAD_REQUEST, "SMS 인증번호가 일치하지 않음", null),

    // 공통 에러
    INPUT_NOT_VALID("300001", HttpStatus.BAD_REQUEST, "입력값이 유효하지 않음", null),

    //알 수 없음
    UNEXPECTED("999999", HttpStatus.INTERNAL_SERVER_ERROR, "UnexpectedError", null);
}