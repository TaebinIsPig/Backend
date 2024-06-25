package com.project.school.domain.schedule.adapter.output.persistence.repository

import com.project.school.domain.account.adapter.output.persistence.entity.AccountEntity
import com.project.school.domain.schedule.adapter.output.persistence.entity.ScheduleEntity
import org.springframework.data.repository.CrudRepository

interface ScheduleRepository: CrudRepository<ScheduleEntity, Long> {

    fun findAllByDateAndAccount(date: String, account: AccountEntity): List<ScheduleEntity>

}
