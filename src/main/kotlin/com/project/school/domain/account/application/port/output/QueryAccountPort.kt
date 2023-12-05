package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.Account
import java.util.UUID

interface QueryAccountPort {

    fun existsById(id: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun findByIdOrNull(id: String): Account?

    fun findByIdxOrNull(idx: UUID): Account?

    fun findByPhoneNumberOrNull(phoneNumber: String): Account?

}