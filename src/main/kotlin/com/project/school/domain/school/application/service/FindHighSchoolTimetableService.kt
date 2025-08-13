package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.HighSchoolTimetableResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindHighSchoolTimetableUseCase
import com.project.school.domain.school.application.port.output.FindHighSchoolTimetablePort
import com.project.school.domain.school.application.port.output.cache.CachePort

@ServiceWithReadOnlyTransaction
class FindHighSchoolTimetableService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisFindHighSchoolTimetablePort: FindHighSchoolTimetablePort,
    private val neisProperties: NeisProperties,
    private val cachePort: CachePort
) : FindHighSchoolTimetableUseCase {

    override fun execute(grade: String, classNum: String, date: String): HighSchoolTimetableResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheName = "highSchoolTimetable"
        val cacheKey = "${account.school.adminCode}/$grade/$classNum/$date"

        cachePort.get(cacheName, cacheKey, HighSchoolTimetableResponse::class.java)?.let {
            return it
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

        val response = HighSchoolTimetableResponse(highSchoolTimetable)
        cachePort.put(cacheName, cacheKey, response)

        return response
    }

}
