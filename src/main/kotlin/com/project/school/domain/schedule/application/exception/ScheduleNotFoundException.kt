package com.project.school.domain.schedule.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class ScheduleNotFoundException: SchoolException(ErrorCode.SCHEDULE_NOT_FOUND)
