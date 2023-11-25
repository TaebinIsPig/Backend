package com.project.school.domain.account.application.port.input

import com.project.school.domain.account.application.port.input.dto.SignUpDto

interface SignUpUseCase {

    fun execute(dto: SignUpDto)

}