package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.common.util.AuthenticationValidator
import com.project.school.domain.account.application.event.DeleteAuthenticationEvent
import com.project.school.domain.account.application.exception.DuplicatedAccountIdException
import com.project.school.domain.account.application.port.input.SignUpUseCase
import com.project.school.domain.account.application.port.input.dto.SignUpDto
import com.project.school.domain.account.application.port.output.CommandAccountPort
import com.project.school.domain.account.application.port.output.PasswordEncodePort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.account.domain.Account
import com.project.school.domain.account.domain.Authority
import com.project.school.domain.account.domain.StudentNumber
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ServiceWithTransaction
class SignUpService(
    private val commandAccountPort: CommandAccountPort,
    private val queryAccountPort: QueryAccountPort,
    private val passwordEncodePort: PasswordEncodePort,
    private val authenticationValidator: AuthenticationValidator,
    private val publisher: ApplicationEventPublisher
): SignUpUseCase {

    override fun execute(dto: SignUpDto) {
        if (queryAccountPort.existById(dto.id)) throw DuplicatedAccountIdException()

        val authentication = authenticationValidator.verifyAuthenticationByPhoneNumber(dto.phoneNumber)
        val deleteAuthenticationEvent = DeleteAuthenticationEvent(authentication)
        publisher.publishEvent(deleteAuthenticationEvent)

        val account = dto.let {
            Account(
                accountIdx = UUID.randomUUID(),
                id = it.id,
                password = passwordEncodePort.passwordEncode(it.password),
                name = it.name,
                studentNumber = StudentNumber(
                    it.studentNumber.grade,
                    it.studentNumber.classNum,
                    it.studentNumber.number
                ),
                phoneNumber = it.phoneNumber,
                school = it.school,
                authority = Authority.ROLE_ACCOUNT
            )
        }
        commandAccountPort.saveAccount(account)
    }

}