package com.project.school.domain.account.application.port.output

interface SendMessagePort {

    fun sendMessage(phoneNumber: String, authCode: Int)

}