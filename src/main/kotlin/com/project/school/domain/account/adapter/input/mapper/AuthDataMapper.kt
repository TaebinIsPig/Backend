package com.project.school.domain.account.adapter.input.mapper

import com.project.school.domain.account.adapter.input.data.request.SignUpRequest
import com.project.school.domain.account.application.port.input.dto.SignUpDto
import org.springframework.stereotype.Component

@Component
class AuthDataMapper(
    private val studentNumberDataMapper: StudentNumberDataMapper
) {

    infix fun toDto(request: SignUpRequest): SignUpDto =
        SignUpDto(
            id = request.id,
            password = request.password,
            name = request.name,
            studentNumber = studentNumberDataMapper.toDto(request.studentNumber),
            phoneNumber = request.phoneNumber,
            school = request.school
        )

}