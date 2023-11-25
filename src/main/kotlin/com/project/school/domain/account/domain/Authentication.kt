package com.project.school.domain.account.domain

data class Authentication(
    val phoneNumber: String,
    val authCodeCount: Long,
    val authenticationCount: Long,
    val isVerified: Boolean,
    val expiredAt: Long
)
