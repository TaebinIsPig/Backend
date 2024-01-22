package com.project.school.domain.account.application.port.input.dto

data class UpdateAccountProfileDto(
    val name: String,
    val phoneNumber: String,
    val school: SchoolDto,
    val studentNumber: StudentNumberDto
)
