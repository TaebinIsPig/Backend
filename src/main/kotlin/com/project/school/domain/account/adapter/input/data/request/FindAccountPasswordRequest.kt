package com.project.school.domain.account.adapter.input.data.request

data class FindAccountPasswordRequest(
    val phoneNumber: String,
    val id: String,
    val newPassword: String
)