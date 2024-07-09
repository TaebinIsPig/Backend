package com.project.school.domain.school.application.port.input

import com.project.school.domain.school.adapter.input.data.response.ElementarySchoolTimetableResponse

interface FindElementarySchoolTimetableUseCase {

    fun execute(grade: String, classNum: String, date: String): ElementarySchoolTimetableResponse

}
