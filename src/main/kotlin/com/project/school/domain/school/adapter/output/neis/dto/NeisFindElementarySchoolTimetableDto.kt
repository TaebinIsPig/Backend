package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisFindElementarySchoolTimetableDto(
    @JsonProperty("elsTimetable")
    val elementaryTimetable: List<ElementaryTimetable>
)

data class ElementaryTimetable(
    val head: List<ElementaryTimetableHead>? = null,
    val row: List<ElementaryTimetableRow>? = null
)

data class ElementaryTimetableHead(
    @JsonProperty("RESULT")
    val result: ElementaryTimetableResult? = null
)

data class ElementaryTimetableResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class ElementaryTimetableRow(
    @JsonProperty("PERIO")
    val period: String,
    @JsonProperty("ITRT_CNTNT")
    val subject: String
)
