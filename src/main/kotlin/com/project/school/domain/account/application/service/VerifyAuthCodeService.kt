package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.event.CreateAuthenticationEvent
import com.project.school.domain.account.application.exception.AuthCodeNotFoundException
import com.project.school.domain.account.application.exception.AuthCodeNotMatchException
import com.project.school.domain.account.application.exception.AuthenticationNotFoundException
import com.project.school.domain.account.application.exception.TooManyAuthCodeRequestException
import com.project.school.domain.account.application.port.input.VerifyAuthCodeUseCase
import com.project.school.domain.account.application.port.output.QueryAuthCodePort
import com.project.school.domain.account.application.port.output.QueryAuthenticationPort
import org.springframework.context.ApplicationEventPublisher

@ServiceWithTransaction
class VerifyAuthCodeService(
    private val queryAuthCodePort: QueryAuthCodePort,
    private val queryAuthenticationPort: QueryAuthenticationPort,
    private val publisher: ApplicationEventPublisher
): VerifyAuthCodeUseCase {

    override fun execute(authCode: Int, phoneNumber: String) {
        val authCodeDomain = queryAuthCodePort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthCodeNotFoundException()
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException()

        if (authentication.authCodeCount > 5) throw TooManyAuthCodeRequestException()

        if (authCodeDomain.authCode != authCode) {
            publisher.publishEvent(CreateAuthenticationEvent(authentication.increaseAuthCodeCount()))
            throw AuthCodeNotMatchException()
        }

        publisher.publishEvent(CreateAuthenticationEvent(authentication.certified()))
    }

}