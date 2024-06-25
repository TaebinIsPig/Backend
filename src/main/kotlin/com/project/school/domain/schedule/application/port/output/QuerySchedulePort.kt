package com.project.school.domain.schedule.application.port.output

import com.project.school.domain.account.domain.Account
import com.project.school.domain.schedule.domain.Schedule

interface QuerySchedulePort {

    fun findAllByDateAndAccount(date: String, account: Account): List<Schedule>

}
