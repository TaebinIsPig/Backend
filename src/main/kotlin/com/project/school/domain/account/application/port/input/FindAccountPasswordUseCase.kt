package com.project.school.domain.account.application.port.input

import com.project.school.domain.account.application.port.input.dto.FindAccountPasswordDto

interface FindAccountPasswordUseCase {

    fun execute(dto: FindAccountPasswordDto)

}