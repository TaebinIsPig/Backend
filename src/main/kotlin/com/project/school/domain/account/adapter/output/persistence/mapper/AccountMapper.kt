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
            idx = domain.accountIdx,
            id = domain.id,
            password = domain.password,
            name = domain.name,
            studentNumber = studentNumberMapper.toEntity(domain.studentNumber),
            phoneNumber = domain.phoneNumber,
            school = domain.school,
            authority = domain.authority
        )

}