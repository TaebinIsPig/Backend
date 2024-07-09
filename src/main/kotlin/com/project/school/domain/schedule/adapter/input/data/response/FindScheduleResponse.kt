package com.project.school.domain.schedule.adapter.input.data.response

import com.project.school.domain.schedule.application.port.input.dto.FindScheduleDto

data class FindScheduleResponse(
    val scheduleList: List<FindScheduleDto>
) {
    constructor(): this(listOf())
}
