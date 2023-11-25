package com.project.school.domain.account.application.event

import com.project.school.domain.account.domain.Authentication

data class DeleteAuthenticationEvent(
    val authentication: Authentication
)
