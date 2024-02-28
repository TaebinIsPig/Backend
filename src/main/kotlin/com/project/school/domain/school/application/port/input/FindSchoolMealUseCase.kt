package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.FindSchoolMealDto
import com.project.school.domain.school.application.port.input.dto.SchoolMealDto

interface FindSchoolMealUseCase {

    fun execute(dto: FindSchoolMealDto): List<SchoolMealDto>

}