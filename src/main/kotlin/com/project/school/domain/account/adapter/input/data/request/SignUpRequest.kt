package com.project.school.domain.account.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SignUpRequest(
    @field:NotNull
    @field:Length(min = 6, max = 15)
    val id: String,

    @field:NotNull
    @field:Length(min = 8, max = 20)
    @field:Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*?~])[0-9a-zA-Z!@#$%^&*?~]+$")
    val password: String,

    @field:NotNull
    @field:Size(min = 2, max = 10)
    val name: String,

    val studentNumber: StudentNumberRequest,

    @field:NotNull
    @field:Size(min = 10, max = 11)
    val phoneNumber: String,

    @field:NotNull
    val school: String
)
