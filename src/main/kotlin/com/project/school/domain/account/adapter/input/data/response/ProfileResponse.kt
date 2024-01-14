package com.project.school.domain.account.adapter.input.data.response

data class ProfileResponse(
    val name: String,
    val phoneNumber: String,
    val studentNumber: StudentNumberResponse,
    val school: String
)