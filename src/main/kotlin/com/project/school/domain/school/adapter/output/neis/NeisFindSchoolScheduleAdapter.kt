package com.project.school.domain.school.adapter.output.neis

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.school.domain.school.adapter.output.neis.dto.NeisFindSchoolScheduleDto
import com.project.school.domain.school.adapter.output.neis.dto.NeisResultDto
import com.project.school.domain.school.adapter.output.neis.dto.SchoolScheduleResult
import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolScheduleResponse
import com.project.school.domain.school.application.port.output.FindSchoolSchedulePort
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class NeisFindSchoolScheduleAdapter(
    private val objectMapper: ObjectMapper,
    private val neisSchoolClient: NeisSchoolClient
) : FindSchoolSchedulePort {

    override fun findSchoolSchedule(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        date: String
    ): List<NeisFindSchoolScheduleResponse> {
        val response = neisSchoolClient.findSchoolSchedule(key, type, pIndex, pSize, educationCode, adminCode, date)

        return if (response.contains("SchoolSchedule")) {
            val root = objectMapper.readValue(response, NeisFindSchoolScheduleDto::class.java)

            val head = root.schoolSchedule.firstOrNull { it.head != null }?.head
            val row = root.schoolSchedule.firstOrNull { it.row != null }?.row

            if(head == null || row == null) {
                log.warn("나이스 Open API 오류")
                return emptyResponse()
            }

            val result = head.firstOrNull { it.result != null }?.result ?: SchoolScheduleResult("ERROR", "알 수 없는 오류")
            log.info(result.code)
            log.info(result.message)

            row.map {
                NeisFindSchoolScheduleResponse(
                    eventDate = it.eventDate,
                    eventName = it.eventName
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

    private fun emptyResponse() = emptyList<NeisFindSchoolScheduleResponse>()
}
