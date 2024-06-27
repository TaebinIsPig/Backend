package com.project.school.domain.schedule.adapter.output.persistence

import com.project.school.domain.account.adapter.output.persistence.mapper.AccountMapper
import com.project.school.domain.account.domain.Account
import com.project.school.domain.schedule.adapter.output.persistence.mapper.ScheduleMapper
import com.project.school.domain.schedule.adapter.output.persistence.repository.ScheduleRepository
import com.project.school.domain.schedule.application.port.output.QuerySchedulePort
import com.project.school.domain.schedule.domain.Schedule
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QuerySchedulePersistenceAdapter(
    private val scheduleRepository: ScheduleRepository,
    private val scheduleMapper: ScheduleMapper,
    private val accountMapper: AccountMapper
): QuerySchedulePort {

    override fun findAllByDateAndAccount(date: String, account: Account): List<Schedule> {
        val accountEntity = accountMapper.toEntity(account)
        val scheduleEntity = scheduleRepository.findAllByDateAndAccount(date, accountEntity)
        return scheduleEntity.map { scheduleMapper.toDomain(it)!! }
    }

    override fun findByIdxOrNull(idx: Long): Schedule? {
        val scheduleEntity = scheduleRepository.findByIdOrNull(idx)
        return scheduleMapper.toDomain(scheduleEntity)
    }

}
