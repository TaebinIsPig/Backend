package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.HighSchoolTimetableResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindHighSchoolTimetableUseCase
import com.project.school.domain.school.application.port.output.FindHighSchoolTimetablePort
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@ServiceWithReadOnlyTransaction
class FindHighSchoolTimetableService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisFindHighSchoolTimetablePort: FindHighSchoolTimetablePort,
    private val neisProperties: NeisProperties,
    private val redisTemplate: RedisTemplate<String, Any>
) : FindHighSchoolTimetableUseCase {

    override fun execute(grade: String, classNum: String, date: String): HighSchoolTimetableResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheKey = "highSchoolTimetable:${account.school.adminCode}/$date/$grade/$classNum"
        val cachedData = redisTemplate.opsForValue().get(cacheKey)
        if (cachedData != null) {
            return cachedData as HighSchoolTimetableResponse
        }

        val highSchoolTimetable = neisFindHighSchoolTimetablePort.findHighSchoolTimetable(
            key = neisProperties.authKey,
            type = "json",
            pIndex = 1,
            pSize = 7,
            educationCode = account.school.educationCode,
            adminCode = account.school.adminCode,
            grade = grade,
            classNum = classNum,
            date = date
        )

        val highSchoolTimetableResponse = HighSchoolTimetableResponse(highSchoolTimetable)

        redisTemplate.opsForValue().set(cacheKey, highSchoolTimetableResponse, 24, TimeUnit.HOURS)

        return highSchoolTimetableResponse
    }

}
