package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.application.port.input.dto.SchoolList

data class SchoolSearchResponse(
    val isLast: Boolean,
    val schoolList: List<SchoolList>?
)
