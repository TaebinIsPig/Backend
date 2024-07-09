package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.adapter.output.neis.response.NeisFindMiddleSchoolTimetableResponse

data class MiddleSchoolTimetableResponse(
    val middleSchoolTimetableList: List<NeisFindMiddleSchoolTimetableResponse>
) {
    constructor(): this(listOf())
}
