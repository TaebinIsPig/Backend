package com.project.school.domain.school.adapter.output.neis.response

data class NeisSchoolSearchResponse(
    val listTotalCount: Int?,
    val result: Result,
    val list: List<SchoolListResponse>?
)

data class Result(
    val code: String,
    val message: String
)

data class SchoolListResponse(
    val educationCode: String,
    val adminCode: String,
    val schoolName: String,
    val schoolType: String
)