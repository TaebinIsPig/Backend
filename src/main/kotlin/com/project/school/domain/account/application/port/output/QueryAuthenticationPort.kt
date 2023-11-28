package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.Authentication

interface QueryAuthenticationPort {

    fun findByPhoneNumberOrNull(phoneNumber: String): Authentication?

    fun existsByPhoneNumber(phoneNumber: String): Boolean

}