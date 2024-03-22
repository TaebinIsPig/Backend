package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindMonthSchoolScheduleUseCase
import com.project.school.domain.school.application.port.input.dto.MonthSchoolScheduleDto
import com.project.school.domain.school.application.port.output.FindMonthSchoolSchedulePort

@ServiceWithReadOnlyTransaction
class FindMonthSchoolScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val findMonthSchoolSchedulePort: FindMonthSchoolSchedulePort
): FindMonthSchoolScheduleUseCase {

    override fun execute(date: String): List<MonthSchoolScheduleDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val monthSchoolSchedule = findMonthSchoolSchedulePort.findMonthSchoolSchedule(
            neisProperties.authKey,
            "json",
            1,
            31,
            account.school.educationCode,
            account.school.adminCode,
            date
        )
        return monthSchoolSchedule.map {
            MonthSchoolScheduleDto(
                eventDate = it.eventDate
            )
        }
    }

}