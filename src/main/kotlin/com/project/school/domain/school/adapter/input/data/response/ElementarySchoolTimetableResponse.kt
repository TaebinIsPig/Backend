package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.adapter.output.neis.response.NeisFindElementarySchoolTimetableResponse

data class ElementarySchoolTimetableResponse(
    val elementarySchoolTimetableList: List<NeisFindElementarySchoolTimetableResponse>
) {
    constructor(): this(listOf())
}
