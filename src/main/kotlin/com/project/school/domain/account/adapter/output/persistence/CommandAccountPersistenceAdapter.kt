package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.school.domain.account.adapter.output.persistence.repository.AccountRepository
import com.project.school.domain.account.application.port.output.CommandAccountPort
import com.project.school.domain.account.domain.Account
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandAccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
): CommandAccountPort {

    override fun saveAccount(account: Account): UUID {
        val accountEntity = accountMapper.toEntity(account)
        return accountRepository.save(accountEntity).idx
    }

}