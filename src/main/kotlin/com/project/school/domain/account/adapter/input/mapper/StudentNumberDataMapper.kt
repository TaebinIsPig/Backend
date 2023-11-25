package com.project.school.domain.account.adapter.input.mapper

import com.project.school.domain.account.adapter.input.data.request.StudentNumberRequest
import com.project.school.domain.account.application.port.input.dto.StudentNumberDto
import org.springframework.stereotype.Component

@Component
class StudentNumberDataMapper {

    fun toDto(request: StudentNumberRequest): StudentNumberDto =
        StudentNumberDto(
            grade = request.grade,
            classNum = request.classNum,
            number = request.number
        )

}