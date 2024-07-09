package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.adapter.input.data.response.SchoolMealResponse

interface FindSchoolMealUseCase {

    fun execute(date: String): SchoolMealResponse

}
