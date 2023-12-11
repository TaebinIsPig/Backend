package com.project.school.domain.account.application.port.input.dto

data class ProfileDto(
    val name: String,
    val phoneNumber: String,
    val studentNumber: StudentNumberDto,
    val school: String
)
