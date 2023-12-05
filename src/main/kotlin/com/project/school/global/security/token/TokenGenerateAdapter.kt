package com.project.school.global.security.token

import com.project.school.domain.account.application.port.output.TokenGeneratePort
import com.project.school.domain.account.application.port.output.dto.TokenDto
import com.project.school.domain.account.domain.Authority
import com.project.school.global.security.token.common.properties.JwtExpTimeProperties
import com.project.school.global.security.token.common.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenGenerateAdapter(
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
): TokenGeneratePort {

    override fun generateToken(accountIdx: UUID, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(accountIdx, authority),
            refreshToken = generateRefreshToken(accountIdx),
            accessTokenExpiredAt = jwtExpTimeProperties.accessExp.toLong(),
            refreshTokenExpiredAt = jwtExpTimeProperties.refreshExp.toLong()
        )

    private fun generateAccessToken(accountIdx: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(accountIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .claim(JwtProperties.AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(accountIdx: UUID): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(accountIdx.toString())
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExp * 1000))
            .compact()

}