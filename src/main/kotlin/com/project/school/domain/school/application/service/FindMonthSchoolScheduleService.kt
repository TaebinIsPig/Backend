package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.MonthSchoolScheduleResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindMonthSchoolScheduleUseCase
import com.project.school.domain.school.application.port.output.FindMonthSchoolSchedulePort
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@ServiceWithReadOnlyTransaction
class FindMonthSchoolScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val findMonthSchoolSchedulePort: FindMonthSchoolSchedulePort,
    private val redisTemplate: RedisTemplate<String, Any>
): FindMonthSchoolScheduleUseCase {

    override fun execute(date: String): MonthSchoolScheduleResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheKey = "monthSchoolScheduleList:${account.school.adminCode}/$date"
        val cachedData = redisTemplate.opsForValue().get(cacheKey)
        if (cachedData != null) {
            return cachedData as MonthSchoolScheduleResponse
        }

        val monthSchoolSchedule = findMonthSchoolSchedulePort.findMonthSchoolSchedule(
            neisProperties.authKey,
            "json",
            1,
            31,
            account.school.educationCode,
            account.school.adminCode,
            date
        )

        val monthSchoolScheduleResponse = MonthSchoolScheduleResponse(monthSchoolSchedule)

        redisTemplate.opsForValue().set(cacheKey, monthSchoolScheduleResponse, 24, TimeUnit.HOURS)

        return monthSchoolScheduleResponse
    }

}
