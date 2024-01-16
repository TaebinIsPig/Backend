package com.project.school.domain.school.adapter.input.data.request

import javax.validation.constraints.NotBlank

data class SchoolRequest(
    @field:NotBlank(message = "시도교육청코드는 필수값입니다.")
    val educationCode: String,

    @field:NotBlank(message = "행정표준코드는 필수값입니다.")
    val adminCode: String,

    @field:NotBlank(message = "학교명은 필수값입니다.")
    val schoolName: String,

    @field:NotBlank(message = "학교종류명은 필수값입니다.")
    val schoolType: String
)
