package com.project.school.domain.account.adapter.input.data.request

import com.project.school.domain.school.adapter.input.data.request.SchoolRequest

data class UpdateAccountProfileRequest(
    val name: String,
    val phoneNumber: String,
    val school: SchoolRequest,
    val studentNumber: StudentNumberRequest
)
