package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.RefreshTokenMapper
import com.project.school.domain.account.adapter.output.persistence.repository.RefreshTokenRepository
import com.project.school.domain.account.application.port.output.CommandRefreshTokenPort
import com.project.school.domain.account.domain.RefreshToken
import org.springframework.stereotype.Component

@Component
class CommandRefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
): CommandRefreshTokenPort {

    override fun saveRefreshToken(refreshToken: RefreshToken): String {
        val refreshTokenEntity = refreshTokenMapper.toEntity(refreshToken)
        return refreshTokenRepository.save(refreshTokenEntity).refreshToken
    }

}