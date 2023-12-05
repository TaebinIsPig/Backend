package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.event.SaveRefreshTokenEvent
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.input.ReissueTokenUseCase
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.account.application.port.output.QueryRefreshTokenPort
import com.project.school.domain.account.application.port.output.TokenGeneratePort
import com.project.school.domain.account.application.port.output.TokenParsePort
import com.project.school.domain.account.application.port.output.dto.TokenDto
import com.project.school.domain.account.domain.Account
import com.project.school.global.security.token.common.exception.ExpiredRefreshTokenException
import com.project.school.global.security.token.common.exception.InvalidTokenException
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class ReissueTokenService(
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
    private val queryAccountPort: QueryAccountPort,
    private val tokenGeneratePort: TokenGeneratePort,
    private val tokenParsePort: TokenParsePort,
    private val publisher: ApplicationEventPublisher
): ReissueTokenUseCase {

    override fun execute(refreshToken: String): TokenDto {
        val parsedRefreshToken = tokenParsePort.parseRefreshToken(refreshToken)
            ?: throw InvalidTokenException()

        val refreshTokenDomain = queryRefreshTokenPort.findByRefreshTokenOrNull(parsedRefreshToken)
            ?: throw ExpiredRefreshTokenException()

        val account = queryAccountPort.findByIdxOrNull(refreshTokenDomain.accountIdx)
            ?: throw AccountNotFoundException()

        val token = tokenGeneratePort.generateToken(refreshTokenDomain.accountIdx, account.authority)

        publishSaveRefreshToken(token, account)

        return token
    }

    private fun publishSaveRefreshToken(token: TokenDto, account: Account) {
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(
            token.refreshToken,
            account.accountIdx,
            token.refreshTokenExpiredAt
        )
        publisher.publishEvent(saveRefreshTokenEvent)
    }

}