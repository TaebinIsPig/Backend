package com.project.school.domain.schedule.application.port.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.schedule.adapter.input.data.response.FindScheduleResponse
import com.project.school.domain.schedule.application.port.input.FindScheduleUseCase
import com.project.school.domain.schedule.application.port.input.dto.FindScheduleDto
import com.project.school.domain.schedule.application.port.output.QuerySchedulePort

@ServiceWithReadOnlyTransaction
class FindScheduleService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val querySchedulePort: QuerySchedulePort
) : FindScheduleUseCase {

    override fun execute(date: String): FindScheduleResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val scheduleList = querySchedulePort.findAllByDateAndAccount(date, account).map {
            FindScheduleDto(
                idx = it.idx,
                date = it.date,
                content = it.content
            )
        }

        val response = FindScheduleResponse(scheduleList)

        return response
    }

}
