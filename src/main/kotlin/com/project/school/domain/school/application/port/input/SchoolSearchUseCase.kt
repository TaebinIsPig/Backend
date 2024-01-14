package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.SchoolSearchDto

interface SchoolSearchUseCase {

    fun execute(page: Int, schoolName: String): SchoolSearchDto

}