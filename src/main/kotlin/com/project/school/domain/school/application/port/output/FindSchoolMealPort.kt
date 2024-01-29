package com.project.school.domain.school.application.port.output

import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolMealResponse

interface FindSchoolMealPort {

    fun findSchoolMeal(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        date: String
        ): List<NeisFindSchoolMealResponse>

}