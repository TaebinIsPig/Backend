package com.project.school.domain.account.domain

import com.project.school.common.annotation.RootAggregate

@RootAggregate
data class AuthCode(
    val phoneNumber: String,
    val authCode: Int,
    val expiredAt: Long
)
