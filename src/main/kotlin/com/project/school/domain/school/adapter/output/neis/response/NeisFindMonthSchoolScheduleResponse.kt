package com.project.school.domain.school.adapter.output.neis.response

data class NeisFindMonthSchoolScheduleResponse(
    val eventDate: String?
) {
    constructor(): this(
        eventDate = ""
    )
}
