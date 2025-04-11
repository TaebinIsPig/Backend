package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisSchoolSearchDto(
    val schoolInfo: List<SchoolInfo>
)

data class SchoolInfo(
    val head: List<SchoolInfoHead>? = null,
    val row: List<SchoolInfoRow>? = null
)

data class SchoolInfoHead(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,
    @JsonProperty("RESULT")
    val result: SchoolInfoResult? = null
)

data class SchoolInfoResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class SchoolInfoRow(
    @JsonProperty("SCHUL_NM")
    val schoolName: String,
    @JsonProperty("ATPT_OFCDC_SC_CODE")
    val educationCode: String,
    @JsonProperty("SD_SCHUL_CODE")
    val adminCode: String,
    @JsonProperty("SCHUL_KND_SC_NM")
    val schoolType: String,
    @JsonProperty("ORG_RDNMA")
    val address: String
)
