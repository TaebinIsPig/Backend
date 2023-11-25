package com.project.school.domain.account.application.common.util

import com.project.school.domain.account.application.exception.AuthenticationNotFoundException
import com.project.school.domain.account.application.port.output.QueryAuthenticationPort
import com.project.school.domain.account.domain.Authentication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthenticationValidator(
    private val queryAuthenticationPort: QueryAuthenticationPort
) {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    fun verifyAuthenticationByPhoneNumber(phoneNumber: String): Authentication {
        val authentication = queryAuthenticationPort.findByPhoneNumberOrNull(phoneNumber)
            ?: throw AuthenticationNotFoundException()
        if (!authentication.isVerified) {
            throw AuthenticationNotFoundException()
        }
        return authentication
    }

}