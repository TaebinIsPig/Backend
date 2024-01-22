package com.project.school.domain.account.application.port.input

import com.project.school.domain.account.application.port.input.dto.UpdateAccountProfileDto

interface UpdateAccountProfileUseCase {

    fun execute(dto: UpdateAccountProfileDto)

}