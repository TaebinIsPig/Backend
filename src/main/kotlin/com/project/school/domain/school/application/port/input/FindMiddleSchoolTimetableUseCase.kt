package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.adapter.input.data.response.MiddleSchoolTimetableResponse

interface FindMiddleSchoolTimetableUseCase {

    fun execute(grade: String, classNum: String, date: String): MiddleSchoolTimetableResponse

}
