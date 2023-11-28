package com.project.school.domain.account.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.AuthenticationMapper
import com.project.school.domain.account.adapter.output.persistence.repository.AuthenticationRepository
import com.project.school.domain.account.application.port.output.QueryAuthenticationPort
import com.project.school.domain.account.domain.Authentication
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryAuthenticationPersistenceAdapter(
    private val authenticationRepository: AuthenticationRepository,
    private val authenticationMapper: AuthenticationMapper
): QueryAuthenticationPort {

    override fun findByPhoneNumberOrNull(phoneNumber: String): Authentication? {
        val authenticationEntity = authenticationRepository.findByIdOrNull(phoneNumber)
        return authenticationMapper.toDomain(authenticationEntity)
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return authenticationRepository.existsById(phoneNumber)
    }
}