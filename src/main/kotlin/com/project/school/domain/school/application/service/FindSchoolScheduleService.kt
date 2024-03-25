package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindSchoolScheduleUseCase
import com.project.school.domain.school.application.port.input.dto.SchoolScheduleDto
import com.project.school.domain.school.application.port.output.FindSchoolSchedulePort

@ServiceWithReadOnlyTransaction
class FindSchoolScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val neisFindSchoolSchedulePort: FindSchoolSchedulePort
): FindSchoolScheduleUseCase {

    override fun execute(date: String): List<SchoolScheduleDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val schoolSchedule = neisFindSchoolSchedulePort.findSchoolSchedule(
            key = neisProperties.authKey,
            "json",
            1,
            10,
            account.school.educationCode,
            account.school.adminCode,
            date
        )
        return schoolSchedule.map {
            SchoolScheduleDto(
                eventDate = it.eventDate,
                eventName = it.eventName
            )
        }
    }

}
