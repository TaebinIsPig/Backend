package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.HighSchoolTimetableDto

interface FindHighSchoolTimetableUseCase {

    fun execute(grade: String, classNum: String, date: String): List<HighSchoolTimetableDto>

}
