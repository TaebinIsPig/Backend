package com.project.school.domain.schedule.application.port.output

import com.project.school.domain.schedule.domain.Schedule

interface CommandSchedulePort {

    fun saveSchedule(schedule: Schedule)

}