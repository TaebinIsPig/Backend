package com.project.school.domain.schedule.application.port.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.schedule.adapter.input.data.response.FindScheduleResponse
import com.project.school.domain.schedule.application.port.input.FindScheduleUseCase
import com.project.school.domain.schedule.application.port.input.dto.FindScheduleDto
import com.project.school.domain.schedule.application.port.output.QuerySchedulePort
import com.project.school.domain.school.adapter.input.data.response.SchoolScheduleResponse
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@ServiceWithReadOnlyTransaction
class FindScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val querySchedulePort: QuerySchedulePort,
    private val redisTemplate: RedisTemplate<String, Any>
) : FindScheduleUseCase {

    override fun execute(date: String): FindScheduleResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheKey = "scheduleList:$accountIdx/$date"
        val cachedData = redisTemplate.opsForValue().get(cacheKey)
        if (cachedData != null) {
            return cachedData as FindScheduleResponse
        }

        val scheduleList = querySchedulePort.findAllByDateAndAccount(date, account).map {
            FindScheduleDto(
                idx = it.idx,
                date = it.date,
                content = it.content
            )
        }
        val scheduleResponse = FindScheduleResponse(scheduleList)

        redisTemplate.opsForValue().set(cacheKey, scheduleResponse, 24, TimeUnit.HOURS)

        return scheduleResponse
    }

}
