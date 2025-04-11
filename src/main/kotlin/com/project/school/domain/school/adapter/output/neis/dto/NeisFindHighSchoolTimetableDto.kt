package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisFindHighSchoolTimetableDto(
    @JsonProperty("hisTimetable")
    val highTimetable: List<HighTimetable>
)

data class HighTimetable(
    val head: List<HighTimetableHead>? = null,
    val row: List<HighTimetableRow>? = null
)

data class HighTimetableHead(
    @JsonProperty("RESULT")
    val result: HighTimetableResult? = null
)

data class HighTimetableResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class HighTimetableRow(
    @JsonProperty("PERIO")
    val period: String,
    @JsonProperty("ITRT_CNTNT")
    val subject: String
)
