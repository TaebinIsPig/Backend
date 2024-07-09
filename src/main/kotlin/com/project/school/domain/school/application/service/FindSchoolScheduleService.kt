package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.SchoolScheduleResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindSchoolScheduleUseCase
import com.project.school.domain.school.application.port.output.FindSchoolSchedulePort
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@ServiceWithReadOnlyTransaction
class FindSchoolScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val neisFindSchoolSchedulePort: FindSchoolSchedulePort,
    private val redisTemplate: RedisTemplate<String, Any>
) : FindSchoolScheduleUseCase {

    override fun execute(date: String): SchoolScheduleResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheKey = "schoolScheduleList:${account.school.adminCode}/$date"
        val cachedData = redisTemplate.opsForValue().get(cacheKey)
        if (cachedData != null) {
            return cachedData as SchoolScheduleResponse
        }

        val schoolSchedule = neisFindSchoolSchedulePort.findSchoolSchedule(
            key = neisProperties.authKey,
            "json",
            1,
            10,
            account.school.educationCode,
            account.school.adminCode,
            date
        )

        val schoolScheduleResponse = SchoolScheduleResponse(schoolSchedule)

        redisTemplate.opsForValue().set(cacheKey, schoolScheduleResponse, 24, TimeUnit.HOURS)

        return schoolScheduleResponse
    }

}
