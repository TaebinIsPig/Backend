package com.project.school.domain.account.application.port.input

interface LogoutAccountUseCase {

    fun execute(refreshToken: String)

}