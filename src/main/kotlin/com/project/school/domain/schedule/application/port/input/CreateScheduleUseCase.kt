package com.project.school.domain.schedule.application.port.input

import com.project.school.domain.schedule.application.port.input.dto.CreateScheduleDto

interface CreateScheduleUseCase {

    fun execute(dto: CreateScheduleDto)

}