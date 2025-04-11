package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisFindMiddleSchoolTimetableDto(
    @JsonProperty("misTimetable")
    val middleTimetable: List<MiddleTimetable>
)

data class MiddleTimetable(
    val head: List<MiddleTimetableHead>? = null,
    val row: List<MiddleTimetableRow>? = null
)

data class MiddleTimetableHead(
    @JsonProperty("RESULT")
    val result: MiddleTimetableResult? = null
)

data class MiddleTimetableResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class MiddleTimetableRow(
    @JsonProperty("PERIO")
    val period: String,
    @JsonProperty("ITRT_CNTNT")
    val subject: String
)
