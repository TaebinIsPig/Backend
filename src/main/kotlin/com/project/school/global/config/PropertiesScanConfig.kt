package com.project.school.global.config

import com.project.school.domain.account.adapter.output.message.properties.CoolSmsProperties
import com.project.school.global.security.token.properties.JwtExpTimeProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        CoolSmsProperties::class,
        JwtExpTimeProperties::class
    ]
)
class PropertiesScanConfig