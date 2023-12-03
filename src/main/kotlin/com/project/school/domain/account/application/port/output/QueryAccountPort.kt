package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.Account

interface QueryAccountPort {

    fun existsById(id: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun findByIdOrNull(id: String): Account?

    fun findByPhoneNumberOrNull(phoneNumber: String): Account?

}