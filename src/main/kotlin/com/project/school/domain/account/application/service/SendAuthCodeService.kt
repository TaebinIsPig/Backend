package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.event.CreateAuthenticationEvent
import com.project.school.domain.account.application.exception.TooManyAuthenticationRequestException
import com.project.school.domain.account.application.port.input.SendAuthCodeUseCase
import com.project.school.domain.account.application.port.output.CommandAuthCodePort
import com.project.school.domain.account.application.port.output.QueryAuthenticationPort
import com.project.school.domain.account.application.port.output.SendMessagePort
import com.project.school.domain.account.domain.AuthCode
import com.project.school.domain.account.domain.Authentication
import org.springframework.context.ApplicationEventPublisher
import java.util.Random

@ServiceWithTransaction
class SendAuthCodeService(
    private val sendMessagePort: SendMessagePort,
    private val commandAuthCodePort: CommandAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher
): SendAuthCodeUseCase {

    override fun execute(phoneNumber: String) {
        val isExistsAuthentication = queryAuthenticationPort.existsByPhoneNumber(phoneNumber)

        if (isExistsAuthentication) {
            val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            if (authentication!!.authenticationCount > 5) {
                throw TooManyAuthenticationRequestException()
            }

            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseAuthenticationCount()))
        }

        val code = createCode()
        sendMessagePort.sendMessage(phoneNumber, code)
        val authCode = AuthCode(
            phoneNumber = phoneNumber,
            authCode = code,
            expiredAt = 300
        )
        commandAuthCodePort.saveAuthCode(authCode)

        if (!isExistsAuthentication) {
            val authentication = Authentication(
                phoneNumber = phoneNumber,
                authCodeCount = 0,
                authenticationCount = 0,
                isVerified = false,
                expiredAt = Authentication.EXPIRED_AT
            )
            publisher.publishEvent(CreateAuthenticationEvent(authentication))
        }

    }

    private fun createCode() = Random().nextInt(8888) + 1111

}