package com.project.school.domain.account.application.port.input

import com.project.school.domain.account.application.port.output.dto.TokenDto

interface ReissueTokenUseCase {

    fun execute(refreshToken: String): TokenDto

}