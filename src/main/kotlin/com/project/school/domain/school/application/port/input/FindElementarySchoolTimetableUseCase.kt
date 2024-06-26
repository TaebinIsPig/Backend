package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.application.port.input.dto.ElementarySchoolTimetableDto

interface FindElementarySchoolTimetableUseCase {

    fun execute(grade: String, classNum: String, date: String): List<ElementarySchoolTimetableDto>

}
