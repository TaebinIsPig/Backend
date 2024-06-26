package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindElementarySchoolTimetableUseCase
import com.project.school.domain.school.application.port.input.dto.ElementarySchoolTimetableDto
import com.project.school.domain.school.application.port.output.FindElementarySchoolTimetablePort

@ServiceWithReadOnlyTransaction
class FindElementarySchoolTimetableService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisFindElementarySchoolTimetablePort: FindElementarySchoolTimetablePort,
    private val neisProperties: NeisProperties
) : FindElementarySchoolTimetableUseCase {

    override fun execute(grade: String, classNum: String, date: String): List<ElementarySchoolTimetableDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
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

        val elementarySchoolTimetableResponse = elementarySchoolTimetable.map {
            ElementarySchoolTimetableDto(
                period = it.period,
                subject = it.subject
            )
        }

        return elementarySchoolTimetableResponse
    }

}
