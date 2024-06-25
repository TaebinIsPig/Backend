package com.project.school.domain.schedule.application.port.output

import com.project.school.domain.schedule.domain.Schedule
import java.util.UUID

interface CommandSchedulePort {

    fun saveSchedule(schedule: Schedule)

    fun deleteAllByAccount(accountId: UUID)

}
