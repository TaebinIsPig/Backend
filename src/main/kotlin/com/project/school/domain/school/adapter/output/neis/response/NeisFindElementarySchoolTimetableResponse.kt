package com.project.school.domain.school.adapter.output.neis.response

data class NeisFindElementarySchoolTimetableResponse(
    val period: String?,
    val subject: String?
) {
    constructor(): this(
        period = "",
        subject = ""
    )
}
