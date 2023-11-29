package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.Account

interface QueryAccountPort {

    fun existById(id: String): Boolean
    fun findByIdOrNull(id: String): Account?

}