package com.project.school.domain.schedule.adapter.input.mapper

import com.project.school.domain.schedule.adapter.input.data.request.CreateScheduleRequest
import com.project.school.domain.schedule.application.port.input.dto.CreateScheduleDto
import org.springframework.stereotype.Component

@Component
class ScheduleDataMapper {

    infix fun toDto(request: CreateScheduleRequest): CreateScheduleDto =
        CreateScheduleDto(
            date = request.date,
            content = request.content
        )

}