package com.project.school.domain.account.application.port.input

import com.project.school.domain.account.application.port.input.dto.SignInDto
import com.project.school.domain.account.application.port.output.dto.TokenDto

interface SignInUseCase {

    fun execute(dto: SignInDto): TokenDto

}