package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.MonthSchoolScheduleResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindMonthSchoolScheduleUseCase
import com.project.school.domain.school.application.port.output.FindMonthSchoolSchedulePort
import com.project.school.domain.school.application.port.output.cache.CachePort

@ServiceWithReadOnlyTransaction
class FindMonthSchoolScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val findMonthSchoolSchedulePort: FindMonthSchoolSchedulePort,
    private val cachePort: CachePort
): FindMonthSchoolScheduleUseCase {

    override fun execute(date: String): MonthSchoolScheduleResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheName = "monthSchoolSchedule"
        val cacheKey = "${account.school.adminCode}/$date"

        cachePort.get(cacheName, cacheKey, MonthSchoolScheduleResponse::class.java)?.let {
            return it
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

        val response = MonthSchoolScheduleResponse(monthSchoolSchedule)
        cachePort.put(cacheName, cacheKey, response)

        return response
    }

}
