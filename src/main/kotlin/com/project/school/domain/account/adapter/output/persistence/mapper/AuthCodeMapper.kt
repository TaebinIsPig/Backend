package com.project.school.domain.account.adapter.output.persistence.mapper

import com.project.school.domain.account.adapter.output.persistence.entity.AuthCodeEntity
import com.project.school.domain.account.domain.AuthCode
import org.springframework.stereotype.Component

@Component
class AuthCodeMapper {

    fun toEntity(authCode: AuthCode): AuthCodeEntity =
        AuthCodeEntity(
            authCode.phoneNumber,
            authCode.authCode,
            authCode.expiredAt
        )

    fun toDomain(authCodeEntity: AuthCodeEntity?): AuthCode? =
        authCodeEntity?.let {
            AuthCode(
                phoneNumber = it.phoneNumber,
                authCode = authCodeEntity.authCode,
                expiredAt = authCodeEntity.expiredAt
            )
        }

}