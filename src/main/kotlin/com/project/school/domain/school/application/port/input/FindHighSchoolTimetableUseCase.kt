package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.adapter.input.data.response.HighSchoolTimetableResponse

interface FindHighSchoolTimetableUseCase {

    fun execute(grade: String, classNum: String, date: String): HighSchoolTimetableResponse

}
