package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.SchoolMealDto

interface FindSchoolMealUseCase {

    fun execute(date: String): List<SchoolMealDto>

}