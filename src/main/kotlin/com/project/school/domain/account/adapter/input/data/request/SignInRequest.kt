package com.project.school.domain.account.adapter.input.data.request

import javax.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank(message = "아이디는 필수값입니다.")
    val id: String,

    @field:NotBlank(message = "비밀번호는 필수값입니다.")
    val password: String
)