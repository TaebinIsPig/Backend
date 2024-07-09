package com.project.school.domain.schedule.application.port.service

import com.project.school.common.annotation.ServiceWithTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.schedule.adapter.input.data.response.FindScheduleResponse
import com.project.school.domain.schedule.application.exception.ScheduleNotFoundException
import com.project.school.domain.schedule.application.port.input.UpdateScheduleUseCase
import com.project.school.domain.schedule.application.port.input.dto.FindScheduleDto
import com.project.school.domain.schedule.application.port.input.dto.UpdateScheduleDto
import com.project.school.domain.schedule.application.port.output.CommandSchedulePort
import com.project.school.domain.schedule.application.port.output.QuerySchedulePort
import com.project.school.domain.schedule.domain.Schedule
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@ServiceWithTransaction
class UpdateScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val querySchedulePort: QuerySchedulePort,
    private val commandSchedulePort: CommandSchedulePort,
    private val redisTemplate: RedisTemplate<String, Any>
) : UpdateScheduleUseCase {

    override fun execute(idx: Long, dto: UpdateScheduleDto) {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val schedule = querySchedulePort.findByIdxOrNull(idx)
            ?: throw ScheduleNotFoundException()

        val cacheKey = "scheduleList:$accountIdx/${schedule.date}"

        val updateSchedule = schedule.updateSchedule(dto.content)

        commandSchedulePort.saveSchedule(updateSchedule)
        updateScheduleCache(cacheKey, updateSchedule)
    }

    private fun updateScheduleCache(cacheKey: String, schedule: Schedule) {
        val cacheData = redisTemplate.opsForValue().get(cacheKey) as? FindScheduleResponse

        if (cacheData != null) {
            val updateScheduleList = cacheData.scheduleList.map {
                if (it.idx == schedule.idx) {
                    FindScheduleDto(
                        idx = schedule.idx,
                        date = schedule.date,
                        content = schedule.content
                    )
                } else {
                    it
                }
            }
            redisTemplate.opsForValue().set(cacheKey, FindScheduleResponse(updateScheduleList), 24, TimeUnit.HOURS)
        }
    }

}
