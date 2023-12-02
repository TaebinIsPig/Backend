package com.project.school.domain.account.adapter.input.data.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "아이디는 필수값입니다.")
    @field:Length(min = 6, max = 15, message = "id의 글자 수는 8 ~ 15자 사이여야 합니다.")
    val id: String,

    @field:NotBlank(message = "비밀번호는 필수값입니다.")
    @field:Length(min = 8, max = 20, message = "비밀번호의 글자 수는 8 ~ 20자 사이여야 합니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*0-9)(?=.*[!@#\$%^&*?~])[a-zA-Z0-9!@#$%^&*?~]{8,20}$",
        message = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
    )
    val password: String,

    @field:NotBlank(message = "이름은 필수값입니다.")
    @field:Size(min = 2, max = 10, message = "이름의 글자 수는 2 ~ 10자 사이여야 합니다.")
    val name: String,

    val studentNumber: StudentNumberRequest,

    @field:NotBlank(message = "전화번호는 필수값입니다.")
    @field:Size(min = 10, max = 11, message = "전화번호의 글자 수는 10 ~ 11자 사이여야 합니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "학교는 필수값입니다.")
    val school: String
)
