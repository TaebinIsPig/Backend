package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisFindMonthSchoolScheduleDto(
    @JsonProperty("SchoolSchedule")
    val monthSchoolSchedule: List<MonthSchoolSchedule>
)

data class MonthSchoolSchedule(
    val head: List<MonthSchoolScheduleHead>? = null,
    val row: List<MonthSchoolScheduleRow>? = null
)

data class MonthSchoolScheduleHead(
    @JsonProperty("RESULT")
    val result: MonthSchoolScheduleResult? = null
)

data class MonthSchoolScheduleResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class MonthSchoolScheduleRow(
    @JsonProperty("AA_YMD")
    val eventDate: String,
)
