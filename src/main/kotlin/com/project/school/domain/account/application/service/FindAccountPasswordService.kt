package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.common.util.AuthenticationValidator
import com.project.school.domain.account.application.event.DeleteAuthenticationEvent
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.input.FindAccountPasswordUseCase
import com.project.school.domain.account.application.port.input.dto.FindAccountPasswordDto
import com.project.school.domain.account.application.port.output.CommandAccountPort
import com.project.school.domain.account.application.port.output.PasswordEncodePort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class FindAccountPasswordService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): FindAccountPasswordUseCase {

    override fun execute(dto: FindAccountPasswordDto) {
        val account = queryAccountPort.findByPhoneNumberOrNull(dto.phoneNumber)
            ?: throw AccountNotFoundException()

        queryAccountPort.findByIdOrNull(dto.id) ?: throw AccountNotFoundException()

        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(account.phoneNumber)
        publisher.publishEvent(DeleteAuthenticationEvent(authentication))

        val encodedNewPassword = passwordEncodePort.passwordEncode(dto.newPassword)
        commandAccountPort.saveAccount(
            account.updateEncodedPassword(encodedNewPassword)
        )
    }

}