package com.project.school.domain.account.application.port.input

interface AccountWithdrawUseCase {

    fun execute(phoneNumber: String)

}