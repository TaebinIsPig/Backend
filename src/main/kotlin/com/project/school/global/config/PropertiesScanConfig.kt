package com.project.school.global.config

import com.project.school.domain.account.adapter.output.message.properties.CoolSmsProperties
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.global.security.token.common.properties.JwtExpTimeProperties
import com.project.school.global.security.token.common.properties.JwtProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        CoolSmsProperties::class,
        JwtExpTimeProperties::class,
        JwtProperties::class,
        NeisProperties::class
    ]
)
class PropertiesScanConfig