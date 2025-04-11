package com.project.school.domain.school.adapter.output.neis

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.school.domain.school.adapter.output.neis.dto.NeisResultDto
import com.project.school.domain.school.adapter.output.neis.dto.NeisSchoolSearchDto
import com.project.school.domain.school.adapter.output.neis.dto.SchoolInfoResult
import com.project.school.domain.school.adapter.output.neis.response.NeisSchoolSearchResponse
import com.project.school.domain.school.adapter.output.neis.response.SchoolListResponse
import com.project.school.domain.school.application.port.output.SchoolSearchPort
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class NeisSchoolSearchAdapter(
    private val objectMapper: ObjectMapper,
    private val client: NeisSchoolClient
) : SchoolSearchPort {

    override fun schoolSearch(
        type: String,
        key: String,
        pIndex: Int,
        pSize: Int,
        schoolName: String
    ): NeisSchoolSearchResponse {
        val response = client.schoolSearch(key, type, pIndex, pSize, schoolName)

        return if (response.contains("schoolInfo")) {
            val root = objectMapper.readValue(response, NeisSchoolSearchDto::class.java)

            val head = root.schoolInfo.firstOrNull { it.head != null }?.head
            val row = root.schoolInfo.firstOrNull { it.row != null }?.row

            if (head == null || row == null) {
                log.warn("나이스 Open API 오류")
                return emptyResponse()
            }

            val totalCount = head.firstOrNull { it.listTotalCount != null }?.listTotalCount ?: 0
            val result = head.firstOrNull { it.result != null }?.result ?: SchoolInfoResult("ERROR", "알 수 없는 오류")

            log.info(result.code)
            log.info(result.message)

            val schoolList = row.map {
                SchoolListResponse(
                    educationCode = it.educationCode,
                    adminCode = it.adminCode,
                    schoolName = it.schoolName,
                    schoolType = it.schoolType,
                    address = it.address
                )
            }

            NeisSchoolSearchResponse(
                listTotalCount = totalCount,
                list = schoolList
            )
        } else {
            val wrapper = objectMapper.readValue(response, NeisResultDto::class.java)
            val result = wrapper.result

            log.info(result.code)
            log.info(result.message)

            NeisSchoolSearchResponse(
                listTotalCount = 0,
                list = null
            )
        }
    }

    private fun emptyResponse() = NeisSchoolSearchResponse(
        listTotalCount = 0,
        list = null
    )

}
