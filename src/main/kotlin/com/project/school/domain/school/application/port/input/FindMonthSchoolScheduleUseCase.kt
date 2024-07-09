package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.adapter.input.data.response.MonthSchoolScheduleResponse

interface FindMonthSchoolScheduleUseCase {

    fun execute(date: String): MonthSchoolScheduleResponse

}
