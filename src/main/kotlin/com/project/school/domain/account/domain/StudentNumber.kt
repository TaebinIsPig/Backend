package com.project.school.domain.account.domain

import com.project.school.common.annotation.Aggregate

@Aggregate
data class StudentNumber(
    var grade: Int,
    var classNum: Int,
    var number: Int
)
