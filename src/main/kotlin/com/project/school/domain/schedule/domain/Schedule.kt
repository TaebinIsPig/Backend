package com.project.school.domain.schedule.domain

import com.project.school.common.annotation.RootAggregate
import com.project.school.domain.account.domain.Account

@RootAggregate
data class Schedule(
    val idx: Long,
    val date: String,
    var content: String,
    val account: Account
) {

    fun updateSchedule(content: String): Schedule {
        this.content = content
        return this
    }

}
