package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.Authentication

interface CommandAuthenticationPort {

    fun deleteAuthentication(authentication: Authentication)

}