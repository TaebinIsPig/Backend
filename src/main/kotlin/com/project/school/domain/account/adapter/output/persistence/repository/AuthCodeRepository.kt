package com.project.school.domain.account.adapter.output.persistence.repository

import com.project.school.domain.account.adapter.output.persistence.entity.AuthCodeEntity
import org.springframework.data.repository.CrudRepository

interface AuthCodeRepository: CrudRepository<AuthCodeEntity, String>