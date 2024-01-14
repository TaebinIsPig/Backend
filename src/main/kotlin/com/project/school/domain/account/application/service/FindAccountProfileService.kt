package com.project.school.domain.account.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.input.FindAccountProfileUseCase
import com.project.school.domain.account.application.port.input.dto.ProfileDto
import com.project.school.domain.account.application.port.input.dto.StudentNumberDto
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort

@ServiceWithReadOnlyTransaction
class FindAccountProfileService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort
): FindAccountProfileUseCase {

    override fun execute(): ProfileDto {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        return ProfileDto(
            name = account.name,
            phoneNumber = account.phoneNumber,
            studentNumber = StudentNumberDto(
                grade = account.studentNumber.grade,
                classNum = account.studentNumber.classNum,
                number = account.studentNumber.number
            ),
            school = account.school.schoolName
        )
    }

}