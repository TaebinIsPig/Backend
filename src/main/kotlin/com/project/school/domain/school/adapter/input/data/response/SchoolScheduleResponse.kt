package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolScheduleResponse

data class SchoolScheduleResponse(
    val schoolScheduleList: List<NeisFindSchoolScheduleResponse>
) {
    constructor(): this(listOf())
}
