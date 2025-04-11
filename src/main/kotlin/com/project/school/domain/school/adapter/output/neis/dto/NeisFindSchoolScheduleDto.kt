package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisFindSchoolScheduleDto(
    @JsonProperty("SchoolSchedule")
    val schoolSchedule: List<SchoolSchedule>
)

data class SchoolSchedule(
    val head: List<SchoolScheduleHead>? = null,
    val row: List<SchoolScheduleRow>? = null
)

data class SchoolScheduleHead(
    @JsonProperty("RESULT")
    val result: SchoolScheduleResult? = null
)

data class SchoolScheduleResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class SchoolScheduleRow(
    @JsonProperty("AA_YMD")
    val eventDate: String,
    @JsonProperty("EVENT_NM")
    val eventName: String
)
