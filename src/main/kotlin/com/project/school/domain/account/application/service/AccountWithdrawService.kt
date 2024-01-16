package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.common.util.AuthenticationValidator
import com.project.school.domain.account.application.event.DeleteAuthenticationEvent
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.input.AccountWithdrawUseCase
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.CommandAccountPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class AccountWithdrawService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher,
    private val commandAccountPort: CommandAccountPort
): AccountWithdrawUseCase {

    override fun execute(phoneNumber: String) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(phoneNumber)
        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)
        publisher.publishEvent(deleteAuthenticationEvent)

        commandAccountPort.deleteAccount(account)
    }

}