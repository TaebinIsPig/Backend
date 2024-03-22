package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.MonthSchoolScheduleDto

interface FindMonthSchoolScheduleUseCase {

    fun execute(date: String): List<MonthSchoolScheduleDto>

}