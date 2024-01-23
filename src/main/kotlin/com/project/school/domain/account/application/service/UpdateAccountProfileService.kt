package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.common.util.AuthenticationValidator
import com.project.school.domain.account.application.event.DeleteAuthenticationEvent
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.exception.DuplicatedAccountPhoneNumberException
import com.project.school.domain.account.application.port.input.UpdateAccountProfileUseCase
import com.project.school.domain.account.application.port.input.dto.UpdateAccountProfileDto
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.CommandAccountPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class UpdateAccountProfileService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val commandAccountPort: CommandAccountPort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): UpdateAccountProfileUseCase {

    override fun execute(dto: UpdateAccountProfileDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        if (account.phoneNumber != dto.phoneNumber) {
            if (queryAccountPort.existsByPhoneNumber(dto.phoneNumber)) throw DuplicatedAccountPhoneNumberException()
            val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(dto.phoneNumber)
            publisher.publishEvent(DeleteAuthenticationEvent(authentication))
        }

        commandAccountPort.saveAccount(account.updateProfile(
            name = dto.name,
            phoneNumber = dto.phoneNumber,
            school = dto.school,
            studentNumber = dto.studentNumber
        ))
    }

}