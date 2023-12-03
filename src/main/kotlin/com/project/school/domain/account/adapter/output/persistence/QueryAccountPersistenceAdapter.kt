package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.school.domain.account.adapter.output.persistence.repository.AccountRepository
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.account.domain.Account
import org.springframework.stereotype.Component

@Component
class QueryAccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
): QueryAccountPort {

    override fun existsById(id: String): Boolean =
        accountRepository.existsById(id)

    override fun existsByPhoneNumber(phoneNumber: String): Boolean =
        accountRepository.existsByPhoneNumber(phoneNumber)

    override fun findByIdOrNull(id: String): Account? {
        val accountEntity = accountRepository.findById(id)
        return accountMapper.toDomain(accountEntity)
    }

    override fun findByPhoneNumberOrNull(phoneNumber: String): Account? {
        val accountEntity = accountRepository.findByPhoneNumber(phoneNumber)
        return accountMapper.toDomain(accountEntity)
    }

}