package com.project.school.domain.account.adapter.output.persistence.mapper

import com.project.school.domain.account.adapter.output.persistence.entity.AccountEntity
import com.project.school.domain.account.domain.Account
import org.springframework.stereotype.Component

@Component
class AccountMapper(
    private val studentNumberMapper: StudentNumberMapper
) {

    fun toEntity(domain: Account): AccountEntity =
        AccountEntity(
            accountIdx = domain.accountIdx,
            id = domain.id,
            password = domain.password,
            name = domain.name,
            studentNumber = studentNumberMapper.toEntity(domain.studentNumber),
            phoneNumber = domain.phoneNumber,
            school = domain.school,
            authority = domain.authority
        )

    fun toDomain(entity: AccountEntity?): Account? =
        entity?.let {
            Account(
                accountIdx = entity.accountIdx,
                id = entity.id,
                password = entity.password,
                name = entity.name,
                studentNumber = studentNumberMapper.toDomain(entity.studentNumber),
                phoneNumber = entity.phoneNumber,
                school = entity.school,
                authority = entity.authority
            )
        }

}