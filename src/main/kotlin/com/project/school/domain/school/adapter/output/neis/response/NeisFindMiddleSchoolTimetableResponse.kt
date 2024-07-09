package com.project.school.domain.school.adapter.output.neis.response

data class NeisFindMiddleSchoolTimetableResponse(
    val period: String?,
    val subject: String?,
    val result: Result
) {
    constructor(): this(
        period = "",
        subject = "",
        result = Result(
            code = "",
            message = ""
        )
    )
}
