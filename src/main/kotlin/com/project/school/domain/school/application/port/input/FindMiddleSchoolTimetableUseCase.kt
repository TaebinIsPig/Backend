package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.MiddleSchoolTimetableDto

interface FindMiddleSchoolTimetableUseCase {

    fun execute(grade: String, classNum: String, date: String): List<MiddleSchoolTimetableDto>

}
