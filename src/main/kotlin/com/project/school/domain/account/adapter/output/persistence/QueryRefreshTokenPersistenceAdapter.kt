package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.RefreshTokenMapper
import com.project.school.domain.account.adapter.output.persistence.repository.RefreshTokenRepository
import com.project.school.domain.account.application.port.output.QueryRefreshTokenPort
import com.project.school.domain.account.domain.RefreshToken
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
): QueryRefreshTokenPort {

    override fun findByRefreshTokenOrNull(refreshToken: String): RefreshToken? {
        val refreshTokenEntity = refreshTokenRepository.findByIdOrNull(refreshToken)
        return refreshTokenMapper.toDomain(refreshTokenEntity)
    }

}