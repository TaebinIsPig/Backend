package com.project.school.domain.schedule.application.port.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.common.cache.port.CachePort
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.schedule.application.exception.ScheduleNotFoundException
import com.project.school.domain.schedule.application.port.input.UpdateScheduleUseCase
import com.project.school.domain.schedule.application.port.input.dto.UpdateScheduleDto
import com.project.school.domain.schedule.application.port.output.CommandSchedulePort
import com.project.school.domain.schedule.application.port.output.QuerySchedulePort

@ServiceWithTransaction
class UpdateScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val cachePort: CachePort
) : UpdateScheduleUseCase {

    override fun execute(idx: Long, dto: UpdateScheduleDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val schedule = querySchedulePort.findByIdxOrNull(idx)
            ?: throw ScheduleNotFoundException()

        val updateSchedule = schedule.updateSchedule(dto.content)

        commandSchedulePort.saveSchedule(updateSchedule)

        val cacheName = "schedule"
        val cacheKey = "$accountIdx/${schedule.date}"
        cachePort.evict(cacheName, cacheKey)
    }
}
