package com.project.school.domain.school.adapter.output.neis

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.school.domain.school.adapter.output.neis.dto.MonthSchoolScheduleResult
import com.project.school.domain.school.adapter.output.neis.dto.NeisFindMonthSchoolScheduleDto
import com.project.school.domain.school.adapter.output.neis.dto.NeisResultDto
import com.project.school.domain.school.adapter.output.neis.response.NeisFindMonthSchoolScheduleResponse
import com.project.school.domain.school.application.port.output.FindMonthSchoolSchedulePort
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class NeisFindMonthSchoolScheduleAdapter(
    private val objectMapper: ObjectMapper,
    private val neisSchoolClient: NeisSchoolClient
): FindMonthSchoolSchedulePort {

    override fun findMonthSchoolSchedule(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        date: String
    ): List<NeisFindMonthSchoolScheduleResponse> {
        val response = neisSchoolClient.findSchoolSchedule(key, type, pIndex, pSize, educationCode, adminCode, date)

        return if(response.contains("SchoolSchedule")) {
            val root = objectMapper.readValue(response, NeisFindMonthSchoolScheduleDto::class.java)

            val head = root.monthSchoolSchedule.firstOrNull { it.head != null }?.head
            val row = root.monthSchoolSchedule.firstOrNull { it.row != null }?.row

            if (head == null || row == null) {
                log.warn("나이스 Open API 오류")
                return emptyResponse()
            }

            val result = head.firstOrNull { it.result != null }?.result ?: MonthSchoolScheduleResult("Error", "알 수 없는 오류")

            log.info(result.code)
            log.info(result.message)

            row.map {
                NeisFindMonthSchoolScheduleResponse(
                    eventDate = it.eventDate
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

    private fun emptyResponse() = emptyList<NeisFindMonthSchoolScheduleResponse>()

}
