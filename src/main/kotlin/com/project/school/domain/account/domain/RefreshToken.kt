package com.project.school.domain.account.domain

import java.util.*

data class RefreshToken(
    val refreshToken: String,
    val accountIdx: UUID,
    val expiredAt: Long
)
