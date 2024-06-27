package com.project.school.domain.schedule.application.port.input

import com.project.school.domain.schedule.application.port.input.dto.UpdateScheduleDto

interface UpdateScheduleUseCase {

    fun execute(idx: Long, dto: UpdateScheduleDto)

}
