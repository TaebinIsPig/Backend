package com.project.school.domain.schedule.application.port.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.schedule.application.port.input.CreateScheduleUseCase
import com.project.school.domain.schedule.application.port.input.dto.CreateScheduleDto
import com.project.school.domain.schedule.application.port.output.CommandSchedulePort
import com.project.school.domain.schedule.domain.Schedule

@ServiceWithTransaction
class CreateScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val commandSchedulePort: CommandSchedulePort
): CreateScheduleUseCase {

    override fun execute(dto: CreateScheduleDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val schedule = Schedule(
            idx = 0L,
            date = dto.date,
            content = dto.content,
            account = account
        )
        commandSchedulePort.saveSchedule(schedule)
    }

}