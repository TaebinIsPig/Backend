package com.project.school.domain.account.application.port.input

interface SendAuthCodeUseCase {

    fun execute(phoneNumber: String)

}