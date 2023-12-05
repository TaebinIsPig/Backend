package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.event.SaveRefreshTokenEvent
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.exception.PasswordNotMatchException
import com.project.school.domain.account.application.port.input.SignInUseCase
import com.project.school.domain.account.application.port.input.dto.SignInDto
import com.project.school.domain.account.application.port.output.PasswordEncodePort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.account.application.port.output.TokenGeneratePort
import com.project.school.domain.account.application.port.output.dto.TokenDto
import com.project.school.domain.account.domain.Account
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher

private val log = KotlinLogging.logger {  }

@ServiceWithTransaction
class SignInService(
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val tokenGeneratePort: TokenGeneratePort,
    private val publisher: ApplicationEventPublisher
): SignInUseCase {

    override fun execute(dto: SignInDto): TokenDto {
        val account: Account = queryAccountPort.findByIdOrNull(dto.id)
            ?: throw AccountNotFoundException()

        if (!passwordEncodePort.isPasswordMatch(dto.password, account.password)) {
            throw PasswordNotMatchException()
        }

        val token = tokenGeneratePort.generateToken(account.accountIdx, account.authority)

        publishSaveRefreshToken(token, account)

        return token
    }

    private fun publishSaveRefreshToken(token: TokenDto, account: Account) {
        val saveRefreshTokenEvent = SaveRefreshTokenEvent(
            refreshToken = token.refreshToken,
            accountIdx = account.accountIdx,
            expiredAt = token.refreshTokenExpiredAt
        )
        publisher.publishEvent(saveRefreshTokenEvent)
    }

}