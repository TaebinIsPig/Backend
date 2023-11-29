package com.project.school.domain.account.adapter.output.persistence.repository

import com.project.school.domain.account.adapter.output.persistence.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String>