package com.jeekim.server.jphappapi.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.jeekim.server.jphappapi.exception.AuthException
import com.jeekim.server.jphappapi.exception.ErrorCode
import com.jeekim.server.jphappapi.exception.ErrorResponse
import com.jeekim.server.jphappapi.utils.Hospital.HOSPITAL
import com.jeekim.server.jphappapi.utils.logger
import org.springframework.core.annotation.Order

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(3)
class AuthorizationFilter(
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val hospitalKey = request.getHeader("hospital-key")
            val path = request.servletPath
            if (path.startsWith("/drug") || path.startsWith("/prescription")) {
                logger().info("path: $path")
                if(hospitalKey == null) throw AuthException(ErrorCode.HEADER_NOT_FOUND)
                checkHospitalKey(request, hospitalKey)
            }
            filterChain.doFilter(request, response)
        } catch (authException: AuthException) {
            val attributes = RequestContextHolder.currentRequestAttributes() as? ServletRequestAttributes
            attributes?.response?.let {
                sendErrorResponse(it, authException.errorCode)
            }
        }

    }
    private fun sendErrorResponse(response: HttpServletResponse, errorCode: ErrorCode) {
        logger().error("[AuthException] : {}", errorCode.message)
        response.status = errorCode.httpStatus.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val errorResponse = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message,
            status = errorCode.httpStatus.value()
        )

        ObjectMapper().writeValue(response.outputStream, errorResponse)
    }

    private fun checkHospitalKey(request: HttpServletRequest, id: String){
        HOSPITAL.firstOrNull { it.id == id} ?: throw AuthException(ErrorCode.HOSPITAL_NOT_FOUND)
        request.setAttribute("id", id)
    }
}
