package com.project.school.domain.school.application.port.output

import com.project.school.domain.school.adapter.output.neis.response.NeisFindMonthSchoolScheduleResponse

interface FindMonthSchoolSchedulePort {

    fun findMonthSchoolSchedule(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        date: String
    ): List<NeisFindMonthSchoolScheduleResponse>

}