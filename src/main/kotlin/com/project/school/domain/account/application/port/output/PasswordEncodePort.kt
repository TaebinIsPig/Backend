package com.project.school.domain.account.application.port.output

interface PasswordEncodePort {

    fun passwordEncode(password: String): String

}