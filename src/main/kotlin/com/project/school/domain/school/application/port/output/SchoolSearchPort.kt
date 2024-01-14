package com.project.school.domain.school.application.port.output

import com.project.school.domain.school.adapter.output.neis.response.NeisSchoolSearchResponse

interface SchoolSearchPort {

    fun schoolSearch(type: String, key: String, pIndex: Int, pSize: Int, schoolName: String) : NeisSchoolSearchResponse

}