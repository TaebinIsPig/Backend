package com.project.school.domain.school.adapter.input.data.response

import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolMealResponse

data class SchoolMealResponse(
    val schoolMealList: List<NeisFindSchoolMealResponse>
) {
    constructor(): this(listOf())
}
