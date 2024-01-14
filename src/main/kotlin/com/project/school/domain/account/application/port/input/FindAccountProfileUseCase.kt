package com.project.school.domain.account.application.port.input

import com.project.school.domain.account.application.port.input.dto.ProfileDto

interface FindAccountProfileUseCase {

    fun execute(): ProfileDto

}