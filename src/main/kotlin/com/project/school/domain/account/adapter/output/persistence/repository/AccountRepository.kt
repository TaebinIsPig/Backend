package com.project.school.domain.account.adapter.output.persistence.repository

import com.project.school.domain.account.adapter.output.persistence.entity.AccountEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AccountRepository: CrudRepository<AccountEntity, UUID> {

    fun existsById(id: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findById(id: String): AccountEntity?
    fun findByPhoneNumber(phoneNumber: String): AccountEntity?

}