package com.project.school.domain.account.application.port.input.dto

data class FindAccountPasswordDto(
    val phoneNumber: String,
    val id: String,
    val newPassword: String
)
