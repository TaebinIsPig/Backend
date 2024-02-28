package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindSchoolMealUseCase
import com.project.school.domain.school.application.port.input.dto.SchoolMealDto
import com.project.school.domain.school.application.port.output.FindSchoolMealPort

@ServiceWithReadOnlyTransaction
class FindSchoolMealService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val findSchoolMealPort: FindSchoolMealPort
): FindSchoolMealUseCase {

    override fun execute(date: String): List<SchoolMealDto> {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()
        val schoolMeal = findSchoolMealPort.findSchoolMeal(
            neisProperties.authKey,
            "json",
            1,
            3,
            account.school.educationCode,
            account.school.adminCode,
            date
        )
        return schoolMeal.map {
            SchoolMealDto(
                mealType = it.mealType,
                mealDate = it.mealDate,
                food = it.food,
                calorie = it.calorie
            )
        }
    }

}