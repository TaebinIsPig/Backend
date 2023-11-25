package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.AuthenticationMapper
import com.project.school.domain.account.adapter.output.persistence.repository.AuthenticationRepository
import com.project.school.domain.account.application.port.output.CommandAuthenticationPort
import com.project.school.domain.account.domain.Authentication
import org.springframework.stereotype.Component

@Component
class CommandAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationMapper: AuthenticationMapper
): CommandAuthenticationPort {

    override fun deleteAuthentication(authentication: Authentication) {
        val authenticationEntity = authenticationMapper.toEntity(authentication)
        authenticationRepository.delete(authenticationEntity)
    }

}