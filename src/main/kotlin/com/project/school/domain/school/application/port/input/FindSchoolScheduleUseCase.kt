package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.SchoolScheduleDto

interface FindSchoolScheduleUseCase {

    fun execute(date: String): List<SchoolScheduleDto>

}