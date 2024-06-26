package com.project.school.domain.school.application.port.output

import com.project.school.domain.school.adapter.output.neis.response.NeisFindMiddleSchoolTimetableResponse

interface FindMiddleSchoolTimetablePort {

    fun findMiddleSchoolTimetable(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        grade: String,
        classNum: String,
        date: String
    ): List<NeisFindMiddleSchoolTimetableResponse>

}
