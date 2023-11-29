package com.project.school.domain.account.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.concurrent.TimeUnit


@RedisHash("authentication")
data class AuthenticationEntity(
    @Id
    val phoneNumber: String,

    val authCodeCount: Long,

    val authenticationCount: Long,

    val isVerified: Boolean,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Long
)