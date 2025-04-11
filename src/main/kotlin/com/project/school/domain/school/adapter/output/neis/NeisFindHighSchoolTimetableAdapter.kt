package com.project.school.domain.school.adapter.output.neis

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.school.domain.school.adapter.output.neis.dto.HighTimetableResult
import com.project.school.domain.school.adapter.output.neis.dto.NeisFindHighSchoolTimetableDto
import com.project.school.domain.school.adapter.output.neis.dto.NeisResultDto
import com.project.school.domain.school.adapter.output.neis.response.NeisFindHighSchoolTimetableResponse
import com.project.school.domain.school.application.port.output.FindHighSchoolTimetablePort
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger { }

@Component
class NeisFindHighSchoolTimetableAdapter(
    private val objectMapper: ObjectMapper,
    private val neisSchoolClient: NeisSchoolClient
) : FindHighSchoolTimetablePort {

    override fun findHighSchoolTimetable(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        grade: String,
        classNum: String,
        date: String
    ): List<NeisFindHighSchoolTimetableResponse> {
        val response = neisSchoolClient.findHighSchoolTimeTable(key, type, pIndex, pSize, educationCode, adminCode, grade, classNum, date)

        return if (response.contains("hisTimetable")) {
            val root = objectMapper.readValue(response, NeisFindHighSchoolTimetableDto::class.java)

            val head = root.highTimetable.firstOrNull { it.head != null }?.head
            val row = root.highTimetable.firstOrNull { it.row != null }?.row

            if (head == null || row == null) {
                log.warn("나이스 Open API 오류")
                return emptyResponse()
            }

            val result = head.firstOrNull { it.result != null }?.result ?: HighTimetableResult("ERROR", "알 수 없는 오류")

            log.info(result.code)
            log.info(result.message)

            row.map {
                NeisFindHighSchoolTimetableResponse(
                    period = it.period,
                    subject = it.subject
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

    private fun emptyResponse() = emptyList<NeisFindHighSchoolTimetableResponse>()

}
