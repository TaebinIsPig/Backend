package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindMiddleSchoolTimetableUseCase
import com.project.school.domain.school.application.port.input.dto.MiddleSchoolTimetableDto
import com.project.school.domain.school.application.port.output.FindMiddleSchoolTimetablePort

@ServiceWithReadOnlyTransaction
class FindMiddleSchoolTimetableService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisFindMiddleSchoolTimetablePort: FindMiddleSchoolTimetablePort,
    private val neisProperties: NeisProperties
) : FindMiddleSchoolTimetableUseCase {

    override fun execute(grade: String, classNum: String, date: String): List<MiddleSchoolTimetableDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val middleSchoolTimetable = neisFindMiddleSchoolTimetablePort.findMiddleSchoolTimetable(
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

        val middleSchoolTimetableResponse = middleSchoolTimetable.map {
            MiddleSchoolTimetableDto(
                period = it.period,
                subject = it.subject
            )
        }

        return middleSchoolTimetableResponse
    }

}
