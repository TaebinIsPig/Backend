package com.project.school.domain.account.adapter.input.mapper

import com.project.school.domain.account.adapter.input.data.request.FindAccountPasswordRequest
import com.project.school.domain.account.adapter.input.data.request.UpdateAccountProfileRequest
import com.project.school.domain.account.adapter.input.data.response.ProfileResponse
import com.project.school.domain.account.adapter.input.data.response.StudentNumberResponse
import com.project.school.domain.account.application.port.input.dto.FindAccountPasswordDto
import com.project.school.domain.account.application.port.input.dto.ProfileDto
import com.project.school.domain.account.application.port.input.dto.UpdateAccountProfileDto
import com.project.school.domain.school.adapter.input.mapper.SchoolDataMapper
import org.springframework.stereotype.Component

@Component
class AccountDataMapper(
    private val studentNumberDataMapper: StudentNumberDataMapper,
    private val schoolDataMapper: SchoolDataMapper
) {

    infix fun toDto(request: FindAccountPasswordRequest): FindAccountPasswordDto =
        FindAccountPasswordDto(
            phoneNumber = request.phoneNumber,
            id = request.id,
            newPassword = request.newPassword
        )

    infix fun toResponse(profileDto: ProfileDto): ProfileResponse =
        ProfileResponse(
            name = profileDto.name,
            phoneNumber = profileDto.phoneNumber,
            studentNumber = StudentNumberResponse(
                grade = profileDto.studentNumber.grade,
                classNum = profileDto.studentNumber.classNum,
                number = profileDto.studentNumber.number
            ),
            school = profileDto.school
        )

    infix fun toDto(request: UpdateAccountProfileRequest): UpdateAccountProfileDto =
        UpdateAccountProfileDto(
            name = request.name,
            phoneNumber = request.phoneNumber,
            school = schoolDataMapper.toDto(request.school),
            studentNumber = studentNumberDataMapper.toDto(request.studentNumber)
        )

}