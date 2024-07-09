package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.adapter.output.neis.response.NeisFindMonthSchoolScheduleResponse

data class MonthSchoolScheduleResponse(
    val monthSchoolScheduleList: List<NeisFindMonthSchoolScheduleResponse>
) {
    constructor(): this(listOf())
}
