package com.project.school.domain.schedule.application.port.input.dto

data class FindScheduleDto(
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
