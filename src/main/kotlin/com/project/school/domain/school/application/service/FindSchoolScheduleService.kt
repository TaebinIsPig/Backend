package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.common.cache.port.CachePort
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.SchoolScheduleResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindSchoolScheduleUseCase
import com.project.school.domain.school.application.port.output.FindSchoolSchedulePort

@ServiceWithReadOnlyTransaction
class FindSchoolScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val neisFindSchoolSchedulePort: FindSchoolSchedulePort,
    private val cachePort: CachePort
) : FindSchoolScheduleUseCase {

    override fun execute(date: String): SchoolScheduleResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheName = "schoolSchedule"
        val cacheKey = "${account.school.adminCode}/$date"

        cachePort.get(cacheName, cacheKey, SchoolScheduleResponse::class.java)?.let {
            return it
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

        val response = SchoolScheduleResponse(schoolSchedule)
        cachePort.put(cacheName, cacheKey, response)

        return response
    }

}
