package com.project.school.domain.account.application.event

import com.project.school.domain.account.domain.Authentication

data class CreateAuthenticationEvent(
    val authentication: Authentication
)