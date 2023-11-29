package com.project.school.domain.account.adapter.output.persistence.mapper

import com.project.school.domain.account.adapter.output.persistence.entity.RefreshTokenEntity
import com.project.school.domain.account.domain.RefreshToken
import org.springframework.stereotype.Component

@Component
class RefreshTokenMapper {

    fun toEntity(domain: RefreshToken): RefreshTokenEntity =
        RefreshTokenEntity(
            refreshToken = domain.refreshToken,
            accountIdx = domain.accountIdx,
            expiredAt = domain.expiredAt
        )

}