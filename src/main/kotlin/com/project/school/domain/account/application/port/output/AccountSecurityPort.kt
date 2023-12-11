package com.project.school.domain.account.application.port.output

import java.util.UUID

interface AccountSecurityPort {

    fun getCurrentAccountIdx(): UUID

}