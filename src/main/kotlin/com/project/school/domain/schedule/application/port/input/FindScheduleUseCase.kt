package com.project.school.domain.schedule.application.port.input

import com.project.school.domain.schedule.adapter.input.data.response.FindScheduleResponse

interface FindScheduleUseCase {

    fun execute(date: String): FindScheduleResponse

}
