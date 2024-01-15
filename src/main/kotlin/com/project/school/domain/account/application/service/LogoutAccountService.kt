package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.port.input.LogoutAccountUseCase
import com.project.school.domain.account.application.port.output.CommandRefreshTokenPort
import com.project.school.domain.account.application.port.output.QueryRefreshTokenPort
import com.project.school.domain.account.application.port.output.TokenParsePort
import com.project.school.global.security.token.common.exception.ExpiredRefreshTokenException
import com.project.school.global.security.token.common.exception.InvalidTokenException

@ServiceWithTransaction
class LogoutAccountService(
    private val tokenParsePort: TokenParsePort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : LogoutAccountUseCase {

    override fun execute(refreshToken: String) {
        val parsedRefreshToken = tokenParsePort.parseRefreshToken(refreshToken)
            ?: throw InvalidTokenException()

        val refreshTokenDomain = queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken)
            ?: throw ExpiredRefreshTokenException()

        commandRefreshTokenPort.deleteRefreshToken(refreshTokenDomain)
    }

}