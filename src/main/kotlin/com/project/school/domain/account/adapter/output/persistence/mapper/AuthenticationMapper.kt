package com.project.school.domain.account.adapter.output.persistence.mapper

import com.project.school.domain.account.adapter.output.persistence.entity.AuthenticationEntity
import com.project.school.domain.account.domain.Authentication
import org.springframework.stereotype.Component

@Component
class AuthenticationMapper {

    fun toEntity(domain: Authentication): AuthenticationEntity =
        AuthenticationEntity(
            phoneNumber = domain.phoneNumber,
            authCodeCount = domain.authCodeCount,
            authenticationCount = domain.authenticationCount,
            isVerified = domain.isVerified,
            expiredAt = domain.expiredAt
        )

    fun toDomain(entity: AuthenticationEntity?): Authentication? =
        entity?.let {
            Authentication(
                phoneNumber = entity.phoneNumber,
                authCodeCount = entity.authCodeCount,
                authenticationCount = entity.authenticationCount,
                isVerified = entity.isVerified,
                expiredAt = entity.expiredAt
            )
        }

}