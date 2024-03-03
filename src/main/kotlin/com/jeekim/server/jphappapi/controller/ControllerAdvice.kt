package com.jeekim.server.jphappapi.controller

import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.ErrorResponse
import com.jeekim.server.jphappapi.exception.JphBizException
import com.jeekim.server.jphappapi.utils.logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.Arrays
import javax.validation.ConstraintViolationException
import kotlin.math.min

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(JphBizException::class)
    fun handleExistingCrewException(jphBizException: JphBizException): ResponseEntity<Any> {
        logger().info("handleExistingCrewException: ${jphBizException.getErrorCode().code}")
        return createResponseEntity(jphBizException.getErrorCode())
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidMethodException(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errorCode: ErrorCode = ErrorCode.INPUT_NOT_VALID
        val response: ErrorResponse = ErrorResponse.ofUserMessage(
            errorCode,
            exception.bindingResult.fieldError?.defaultMessage ?: errorCode.message
        )
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body<Any>(response)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleValidException(exception: ConstraintViolationException): ResponseEntity<Any> {
        val errorCode = ErrorCode.INPUT_NOT_VALID
        val response: ErrorResponse = ErrorResponse.ofUserMessage(
            errorCode,
            exception.message ?: errorCode.message
        )
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body<Any>(response)
    }
    @ExceptionHandler(Exception::class)
    fun handleBadRequestException(exception: Exception): ResponseEntity<Any> {
        val errorCode = ErrorCode.UNEXPECTED
        val response: ErrorResponse = ErrorResponse.ofUserMessage(
            errorCode,
            exception.message ?: errorCode.message
        )
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body<Any>(response)
    }

    private fun createResponseEntity(errorCode: ErrorCode): ResponseEntity<Any> {
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode))
    }
}