package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.AuthCodeMapper
import com.project.school.domain.account.adapter.output.persistence.repository.AuthCodeRepository
import com.project.school.domain.account.application.port.output.CommandAuthCodePort
import com.project.school.domain.account.domain.AuthCode
import org.springframework.stereotype.Component

@Component
class CommandAuthCodePersistenceAdapter(
    private val authCodeRepository: AuthCodeRepository,
    private val authCodeMapper: AuthCodeMapper
): CommandAuthCodePort {

    override fun saveAuthCode(authCode: AuthCode): Int {
        val authCodeEntity = authCodeMapper.toEntity(authCode)
        return authCodeRepository.save(authCodeEntity).authCode
    }

}