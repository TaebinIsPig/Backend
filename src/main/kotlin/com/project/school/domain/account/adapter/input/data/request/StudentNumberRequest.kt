package com.project.school.domain.account.adapter.input.data.request

import javax.validation.constraints.NotNull

data class StudentNumberRequest(
    @field:NotNull(message = "학년은 필수값입니다.")
    val grade: Int,

    @field:NotNull(message = "반은 필수값입니다.")
    val classNum: Int,

    @field:NotNull(message = "번호는 필수값입니다.")
    val number: Int
)
