package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.account.application.exception.AccountNotFoundException
import com.project.school.domain.account.application.port.output.AccountSecurityPort
import com.project.school.domain.account.application.port.output.QueryAccountPort
import com.project.school.domain.school.adapter.input.data.response.SchoolMealResponse
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.FindSchoolMealUseCase
import com.project.school.domain.school.application.port.output.FindSchoolMealPort
import com.project.school.domain.school.application.port.output.cache.CachePort

@ServiceWithReadOnlyTransaction
class FindSchoolMealService(
    private val accountSecurityPort: AccountSecurityPort,
    private val queryAccountPort: QueryAccountPort,
    private val neisProperties: NeisProperties,
    private val findSchoolMealPort: FindSchoolMealPort,
    private val cachePort: CachePort
): FindSchoolMealUseCase {

    override fun execute(date: String): SchoolMealResponse {
        val accountIdx = accountSecurityPort.getCurrentAccountIdx()
        val account = queryAccountPort.findByIdxOrNull(accountIdx)
            ?: throw AccountNotFoundException()

        val cacheName = "schoolMeal"
        val cacheKey = "${account.school.adminCode}/$date"

        cachePort.get(cacheName, cacheKey, SchoolMealResponse::class.java)?.let {
            return it
        }

        val schoolMeal = findSchoolMealPort.findSchoolMeal(
            neisProperties.authKey,
            "json",
            1,
            3,
            account.school.educationCode,
            account.school.adminCode,
            date
        )

        val response = SchoolMealResponse(schoolMeal)
        cachePort.put(cacheName, cacheKey, response)

        return response
    }

}
