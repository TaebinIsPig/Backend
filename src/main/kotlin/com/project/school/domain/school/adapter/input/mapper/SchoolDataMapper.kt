package com.project.school.domain.school.adapter.input.mapper

import com.project.school.domain.account.application.port.input.dto.SchoolDto
import com.project.school.domain.school.adapter.input.data.request.SchoolRequest
import com.project.school.domain.school.adapter.input.data.response.MonthSchoolScheduleResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolMealResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolScheduleResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolSearchResponse
import com.project.school.domain.school.application.port.input.dto.MonthSchoolScheduleDto
import com.project.school.domain.school.application.port.input.dto.SchoolMealDto
import com.project.school.domain.school.application.port.input.dto.SchoolScheduleDto
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
    fun toResponse(dto: SchoolMealDto): SchoolMealResponse =
        SchoolMealResponse(
            mealType = dto.mealType,
            mealDate = dto.mealDate,
            food = dto.food,
            calorie = dto.calorie
        )

    fun toResponse(dto: SchoolScheduleDto): SchoolScheduleResponse =
        SchoolScheduleResponse(
            eventDate = dto.eventDate,
            eventName = dto.eventName
        )

    fun toResponse(dto: MonthSchoolScheduleDto): MonthSchoolScheduleResponse =
        MonthSchoolScheduleResponse(
            eventDate = dto.eventDate
        )

}
