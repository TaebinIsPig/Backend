package com.project.school.domain.schedule.adapter.output.persistence.repository

import com.project.school.domain.schedule.adapter.output.persistence.entity.ScheduleEntity
import org.springframework.data.repository.CrudRepository

interface ScheduleRepository: CrudRepository<ScheduleEntity, Long> {
}