package com.project.school.domain.schedule.adapter.input.data.response

data class ScheduleResponse(
    val idx: Long,
    val date: String,
    val content: String
) {
    constructor(): this(
        idx = 0L,
        date = "",
        content = ""
    )
}
