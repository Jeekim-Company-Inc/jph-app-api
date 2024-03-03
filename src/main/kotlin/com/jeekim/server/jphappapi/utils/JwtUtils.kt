package com.jeekim.server.jphappapi.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Date

object JwtUtils {

    private const val EXPIRE = 365 * 24 * 60 * 60 * 1000L // 1년
    private const val SECRET_KEY = "dsfdashofehwlahsfasdflasghasgasdgdsa"

    fun createToken(subject: String): String {
        return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRE))
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .compact()
    }

    fun verifyAndDecodeJwt(token: String): Claims {
        val jws: Jws<Claims> = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
            .build()
            .parseClaimsJws(token)

        // Return the decoded claims
        return jws.body
    }
}