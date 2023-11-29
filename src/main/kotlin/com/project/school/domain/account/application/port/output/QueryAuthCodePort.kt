package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.AuthCode

interface QueryAuthCodePort {

    fun findByPhoneNumberOrNull(phoneNumber: String): AuthCode?

}