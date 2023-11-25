package com.project.school.domain.account.domain

import com.project.school.common.annotation.Aggregate

@Aggregate
data class StudentNumber(
    val grade: Int,
    val classNum: Int,
    val number: Int
)
