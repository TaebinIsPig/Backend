package com.project.school.domain.schedule.adapter.output.persistence

import com.project.school.domain.schedule.adapter.output.persistence.entity.QScheduleEntity.scheduleEntity
import com.project.school.domain.schedule.adapter.output.persistence.mapper.ScheduleMapper
import com.project.school.domain.schedule.adapter.output.persistence.repository.ScheduleRepository
import com.project.school.domain.schedule.application.port.output.CommandSchedulePort
import com.project.school.domain.schedule.domain.Schedule
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandSchedulePersistenceAdapter(
    private val scheduleRepository: ScheduleRepository,
    private val scheduleMapper: ScheduleMapper,
    private val queryFactory: JPAQueryFactory
): CommandSchedulePort {

    override fun saveSchedule(schedule: Schedule) {
        val scheduleEntity = scheduleMapper.toEntity(schedule)
        scheduleRepository.save(scheduleEntity)
    }

    override fun deleteAllByAccount(accountId: UUID) {
        queryFactory.delete(scheduleEntity)
            .where(scheduleEntity.account.accountIdx.eq(accountId))
            .execute()
    }

}
