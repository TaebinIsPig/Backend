package com.project.school.global.security.token.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt.time")
class JwtExpTimeProperties(
    val accessExp: Int,
    val refreshExp: Int
)