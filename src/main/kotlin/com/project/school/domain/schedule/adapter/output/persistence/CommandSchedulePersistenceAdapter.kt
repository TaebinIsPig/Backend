package com.project.school.domain.schedule.adapter.output.persistence

import com.project.school.domain.schedule.adapter.output.persistence.mapper.ScheduleMapper
import com.project.school.domain.schedule.adapter.output.persistence.repository.ScheduleRepository
import com.project.school.domain.schedule.application.port.output.CommandSchedulePort
import com.project.school.domain.schedule.domain.Schedule
import org.springframework.stereotype.Component

@Component
class CommandSchedulePersistenceAdapter(
    private val scheduleRepository: ScheduleRepository,
    private val scheduleMapper: ScheduleMapper
): CommandSchedulePort {

    override fun saveSchedule(schedule: Schedule) {
        val scheduleEntity = scheduleMapper.toEntity(schedule)
        scheduleRepository.save(scheduleEntity)
    }

}