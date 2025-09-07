package com.project.school.domain.school.adapter.output.neis

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.school.domain.school.adapter.output.neis.dto.NeisFindSchoolMealDto
import com.project.school.domain.school.adapter.output.neis.dto.NeisResultDto
import com.project.school.domain.school.adapter.output.neis.dto.SchoolMealResult
import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolMealResponse
import com.project.school.domain.school.application.port.output.FindSchoolMealPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class NeisFindSchoolMealAdapter(
    val objectMapper: ObjectMapper,
    val neisSchoolClient: NeisSchoolClient
): FindSchoolMealPort {

    override fun findSchoolMeal(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        date: String
    ): List<NeisFindSchoolMealResponse> {
        val response = neisSchoolClient.findSchoolMeal(key, type, pIndex, pSize, educationCode, adminCode, date)

        return if (response.contains("mealServiceDietInfo")) {
            val root = objectMapper.readValue(response, NeisFindSchoolMealDto::class.java)

            val head = root.schoolMeal.firstOrNull { it.head != null }?.head
            val row = root.schoolMeal.firstOrNull { it.row != null }?.row

            if (head == null || row == null) {
                log.warn("나이스 Open API 오류")
                return emptyResponse()
            }

            val result = head.firstOrNull { it.result != null }?.result ?: SchoolMealResult("ERROR", "알 수 없는 오류")

            log.info(result.code)
            log.info(result.message)

            row.map {
                NeisFindSchoolMealResponse(
                    mealType = it.mealType,
                    mealDate = it.mealDate,
                    food = it.food.split("<br/>"),
                    calorie = it.calorie
                )
            }
        } else {
            val wrapper = objectMapper.readValue(response, NeisResultDto::class.java)
            val result = wrapper.result

            log.info(result.code)
            log.info(result.message)

            emptyResponse()
        }
    }

    private fun emptyResponse() = emptyList<NeisFindSchoolMealResponse>()

}
