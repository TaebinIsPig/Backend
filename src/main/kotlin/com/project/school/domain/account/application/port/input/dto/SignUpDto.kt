package com.project.school.domain.account.application.port.input.dto

data class SignUpDto(
    val id: String,
    val password: String,
    val name: String,
    val studentNumber: StudentNumberDto,
    val phoneNumber: String,
    val school: String
)
