package com.project.school.domain.account.adapter.input.mapper

import com.project.school.domain.account.adapter.input.data.request.FindAccountPasswordRequest
import com.project.school.domain.account.application.port.input.dto.FindAccountPasswordDto
import org.springframework.stereotype.Component

@Component
class AccountDataMapper {

    infix fun toDto(request: FindAccountPasswordRequest): FindAccountPasswordDto =
        FindAccountPasswordDto(
            phoneNumber = request.phoneNumber,
            id = request.id,
            newPassword = request.newPassword
        )

}