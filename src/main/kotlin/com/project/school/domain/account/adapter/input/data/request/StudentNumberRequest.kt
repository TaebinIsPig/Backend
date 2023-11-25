package com.project.school.domain.account.adapter.input.data.request

import javax.validation.constraints.NotNull

data class StudentNumberRequest(
    @field:NotNull
    val grade: Int,

    @field:NotNull
    val classNum: Int,

    @field:NotNull
    val number: Int
)
