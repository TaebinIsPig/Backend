package com.project.school.domain.school.adapter.output.neis

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.school.domain.school.adapter.output.neis.dto.ElementaryTimetableResult
import com.project.school.domain.school.adapter.output.neis.dto.NeisFindElementarySchoolTimetableDto
import com.project.school.domain.school.adapter.output.neis.dto.NeisResultDto
import com.project.school.domain.school.adapter.output.neis.response.NeisFindElementarySchoolTimetableResponse
import com.project.school.domain.school.application.port.output.FindElementarySchoolTimetablePort
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class NeisFindElementarySchoolTimetableAdapter(
    private val objectMapper: ObjectMapper,
    private val neisSchoolClient: NeisSchoolClient
) : FindElementarySchoolTimetablePort {

    override fun findElementarySchoolTimetable(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        grade: String,
        classNum: String,
        date: String
    ): List<NeisFindElementarySchoolTimetableResponse> {
        val response = neisSchoolClient.findElementarySchoolTimetable(key, type, pIndex, pSize, educationCode, adminCode, grade, classNum, date)

        return if (response.contains("elsTimetable")) {
            val root = objectMapper.readValue(response, NeisFindElementarySchoolTimetableDto::class.java)

            val head = root.elementaryTimetable.firstOrNull { it.head != null }?.head
            val row = root.elementaryTimetable.firstOrNull { it.row != null }?.row

            if (head == null || row == null) {
                log.warn("나이스 Open API 오류")
                return emptyResponse()
            }

            val result = head.firstOrNull { it.result != null }?.result ?: ElementaryTimetableResult("ERROR", "알 수 없는 오류")

            log.info(result.code)
            log.info(result.message)

            row.map {
                NeisFindElementarySchoolTimetableResponse(
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

    private fun emptyResponse() = emptyList<NeisFindElementarySchoolTimetableResponse>()
}
