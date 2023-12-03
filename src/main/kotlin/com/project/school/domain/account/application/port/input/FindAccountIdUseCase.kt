package com.project.school.domain.account.application.port.input

interface FindAccountIdUseCase {

    fun execute(phoneNumber: String): String

}