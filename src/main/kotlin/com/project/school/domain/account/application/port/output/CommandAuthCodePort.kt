package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.AuthCode

interface CommandAuthCodePort {

    fun saveAuthCode(authCode: AuthCode): Int

}