package com.project.school.domain.school.adapter.input.mapper

import com.project.school.domain.account.application.port.input.dto.SchoolDto
import com.project.school.domain.school.adapter.input.data.request.FindSchoolMealRequest
import com.project.school.domain.school.adapter.input.data.request.SchoolRequest
import com.project.school.domain.school.adapter.input.data.response.SchoolMealResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolSearchResponse
import com.project.school.domain.school.application.port.input.dto.FindSchoolMealDto
import com.project.school.domain.school.application.port.input.dto.SchoolMealDto
import com.project.school.domain.school.application.port.input.dto.SchoolSearchDto
import org.springframework.stereotype.Component

@Component
class SchoolDataMapper {

    fun toDto(request: SchoolRequest): SchoolDto =
        SchoolDto(
            educationCode = request.educationCode,
            adminCode = request.adminCode,
            schoolName = request.schoolName,
            schoolType = request.schoolType
        )

    fun toResponse(schoolSearchDto: SchoolSearchDto): SchoolSearchResponse =
        SchoolSearchResponse(
            isLast = schoolSearchDto.isLast,
            schoolList = schoolSearchDto.schoolList
        )

    infix fun toDto(request: FindSchoolMealRequest): FindSchoolMealDto =
        FindSchoolMealDto(
            date = request.date
        )

    fun toResponse(dto: List<SchoolMealDto>): List<SchoolMealResponse> =
        dto.map {
            SchoolMealResponse(
                mealType = it.mealType,
                mealDate = it.mealDate,
                food = it.food,
                calorie = it.calorie
            )
        }

}