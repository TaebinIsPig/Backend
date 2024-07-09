package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.adapter.input.data.response.SchoolScheduleResponse

interface FindSchoolScheduleUseCase {

    fun execute(date: String): SchoolScheduleResponse

}
