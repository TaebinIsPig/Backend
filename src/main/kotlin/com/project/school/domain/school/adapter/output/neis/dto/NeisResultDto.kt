package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisResultDto(
    @JsonProperty("RESULT")
    val result: NeisResult
)

data class NeisResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)
