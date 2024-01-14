package com.project.school.domain.school.adapter.output.neis.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("neis")
data class NeisProperties(
    val authKey: String
)