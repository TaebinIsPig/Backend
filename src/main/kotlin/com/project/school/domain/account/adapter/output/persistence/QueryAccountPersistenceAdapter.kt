package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.repository.AccountRepository
import com.project.school.domain.account.application.port.output.QueryAccountPort
import org.springframework.stereotype.Component

@Component
class QueryAccountPersistenceAdapter(
    private val accountRepository: AccountRepository
): QueryAccountPort {

    override fun existById(id: String): Boolean =
        accountRepository.existsById(id)

}