package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.common.util.AuthenticationValidator
import com.project.school.domain.account.application.event.DeleteAuthenticationEvent
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.input.FindAccountIdUseCase
import com.project.school.domain.account.application.port.output.QueryAccountPort
import org.springframework.context.ApplicationEventPublisher

@ServiceWithReadOnlyTransaction
class FindAccountIdService(
    private val queryAccountPort: QueryAccountPort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): FindAccountIdUseCase {

    override fun execute(phoneNumber: String): String {
        val account = queryAccountPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AccountNotFoundException()
        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(phoneNumber)
        publisher.publishEvent(DeleteAuthenticationEvent(authentication))
        return account.id
    }

}