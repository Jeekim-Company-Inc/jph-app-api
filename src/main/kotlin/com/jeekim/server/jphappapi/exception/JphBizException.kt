package com.jeekim.server.jphappapi.exception


class JphBizException: RuntimeException {
    private val errorCode: ErrorCode
    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }

    constructor(
        errorCode: ErrorCode,
        message: String
    ) : super(message) {
        this.errorCode = errorCode
        this.errorCode.userMessage = message
    }
    fun getErrorCode(): ErrorCode {
        return errorCode
    }
}