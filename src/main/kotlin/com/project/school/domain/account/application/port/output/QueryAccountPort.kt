package com.project.school.domain.account.application.port.output

interface QueryAccountPort {

    fun existById(id: String): Boolean

}