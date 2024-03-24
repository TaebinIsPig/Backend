package com.project.school.domain.school.domain

import com.project.school.common.annotation.Aggregate

@Aggregate
data class School(
    var educationCode: String,
    var adminCode: String,
    var schoolName: String,
    var schoolType: String
)
