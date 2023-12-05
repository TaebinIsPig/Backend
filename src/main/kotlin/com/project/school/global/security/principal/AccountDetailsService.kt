package com.project.school.global.security.principal

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.QueryAccountPort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

@ServiceWithTransaction
class AccountDetailsService(
    private val queryAccountPort: QueryAccountPort
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        queryAccountPort.findByIdxOrNull(UUID.fromString(username))
            .let { it ?: throw AccountNotFoundException() }
            .let { AccountDetails(it.accountIdx) }

}