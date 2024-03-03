package com.jeekim.server.jphappapi.exception

open class AuthException(
    open var errorCode: ErrorCode
) : RuntimeException()