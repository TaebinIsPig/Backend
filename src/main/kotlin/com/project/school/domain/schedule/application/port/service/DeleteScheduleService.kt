package com.project.school.domain.schedule.application.port.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.schedule.application.exception.ScheduleNotFoundException
import com.project.school.domain.schedule.application.port.input.DeleteScheduleUseCase
import com.project.school.domain.schedule.application.port.output.CommandSchedulePort
import com.project.school.domain.schedule.application.port.output.QuerySchedulePort

@ServiceWithTransaction
class DeleteScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort
) : DeleteScheduleUseCase {

    override fun execute(idx: Long) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val schedule = querySchedulePort.findByIdxOrNull(idx)
            ?: throw ScheduleNotFoundException()

        commandSchedulePort.deleteSchedule(schedule)
    }

}
