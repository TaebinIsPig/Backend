package com.project.school.domain.school.application.port.input.dto

import kotlin.collections.List

data class SchoolSearchDto(
    val isLast: Boolean,
    val schoolList: List<SchoolList>?
)

data class SchoolList(
    val educationCode: String?,
    val adminCode: String?,
    val schoolName: String?,
    val schoolType: String?
)