package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.adapter.output.neis.response.NeisFindHighSchoolTimetableResponse

data class HighSchoolTimetableResponse(
    val highSchoolTimetableList: List<NeisFindHighSchoolTimetableResponse>
) {
    constructor(): this(listOf())
}
