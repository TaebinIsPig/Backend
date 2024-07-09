package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.ElementarySchoolTimetableResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindElementarySchoolTimetableUseCase
import com.project.school.domain.school.application.port.output.FindElementarySchoolTimetablePort
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

@ServiceWithReadOnlyTransaction
class FindElementarySchoolTimetableService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisFindElementarySchoolTimetablePort: FindElementarySchoolTimetablePort,
    private val neisProperties: NeisProperties,
    private val redisTemplate: RedisTemplate<String, Any>
) : FindElementarySchoolTimetableUseCase {

    override fun execute(grade: String, classNum: String, date: String): ElementarySchoolTimetableResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheKey = "elementarySchoolTimetable:${account.school.adminCode}/$date/$grade/$classNum"
        val cachedData = redisTemplate.opsForValue().get(cacheKey)
        if (cachedData != null) {
            return cachedData as ElementarySchoolTimetableResponse
        }

        val elementarySchoolTimetable = neisFindElementarySchoolTimetablePort.findElementarySchoolTimetable(
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

        val elementarySchoolTimetableResponse = ElementarySchoolTimetableResponse(elementarySchoolTimetable)

        redisTemplate.opsForValue().set(cacheKey, elementarySchoolTimetableResponse, 24, TimeUnit.HOURS)

        return elementarySchoolTimetableResponse
    }

}
