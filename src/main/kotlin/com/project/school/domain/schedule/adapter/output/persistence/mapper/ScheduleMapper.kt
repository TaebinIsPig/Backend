package com.project.school.domain.schedule.adapter.output.persistence.mapper

import com.project.school.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.school.domain.schedule.adapter.output.persistence.entity.ScheduleEntity
import com.project.school.domain.schedule.domain.Schedule
import org.springframework.stereotype.Component

@Component
class ScheduleMapper(
    private val accountMapper: AccountMapper
) {

    fun toEntity(domain: Schedule): ScheduleEntity =
        ScheduleEntity(
            idx = domain.idx,
            date = domain.date,
            content = domain.content,
            account = accountMapper.toEntity(domain.account)
        )

}