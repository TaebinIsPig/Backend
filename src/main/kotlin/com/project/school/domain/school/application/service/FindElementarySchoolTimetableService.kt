package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.common.cache.port.CachePort
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.ElementarySchoolTimetableResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindElementarySchoolTimetableUseCase
import com.project.school.domain.school.application.port.output.FindElementarySchoolTimetablePort

@ServiceWithReadOnlyTransaction
class FindElementarySchoolTimetableService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisFindElementarySchoolTimetablePort: FindElementarySchoolTimetablePort,
    private val neisProperties: NeisProperties,
    private val cachePort: CachePort
) : FindElementarySchoolTimetableUseCase {

    override fun execute(grade: String, classNum: String, date: String): ElementarySchoolTimetableResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheName = "elementarySchoolTimetable"
        val cacheKey = "${account.school.adminCode}/$date/$grade/$classNum"

        cachePort.get(cacheName, cacheKey, ElementarySchoolTimetableResponse::class.java)?.let {
            return it
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

        val response = ElementarySchoolTimetableResponse(elementarySchoolTimetable)
        cachePort.put(cacheName, cacheKey, response)

        return response
    }

}
