package com.project.school.domain.account.application.event

import java.util.UUID

data class SaveRefreshTokenEvent(
    val refreshToken: String,
    val accountIdx: UUID,
    val expiredAt: Long
)
