package com.project.school.domain.school.adapter.output.neis

import com.project.school.domain.school.adapter.output.neis.response.NeisFindMiddleSchoolTimetableResponse
import com.project.school.domain.school.adapter.output.neis.response.Result
import com.project.school.domain.school.application.port.output.FindMiddleSchoolTimetablePort
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class NeisFindMiddleSchoolTimetableAdapter(
    private val restTemplate: RestTemplate
) : FindMiddleSchoolTimetablePort {

    override fun findMiddleSchoolTimetable(
        key: String,
        type: String,
        pIndex: Int,
        pSize: Int,
        educationCode: String,
        adminCode: String,
        grade: String,
        classNum: String,
        date: String
    ): List<NeisFindMiddleSchoolTimetableResponse> {
        val url = "https://open.neis.go.kr/hub/misTimetable"

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("Key", key)
            .queryParam("Type", type)
            .queryParam("pIndex", pIndex)
            .queryParam("pSize", pSize)
            .queryParam("ATPT_OFCDC_SC_CODE", educationCode)
            .queryParam("SD_SCHUL_CODE", adminCode)
            .queryParam("GRADE", grade)
            .queryParam("CLASS_NM", classNum)
            .queryParam("ALL_TI_YMD", date)
            .build()

        val headers = HttpHeaders()
        val request: HttpEntity<String> = HttpEntity(headers)

        val responseEntity =
            restTemplate.exchange(uri.toString(), HttpMethod.GET, request, String::class.java)

        val middleSchoolTimetableList = mutableListOf<NeisFindMiddleSchoolTimetableResponse>()

        val jsonObject = JSONParser().parse(responseEntity.body) as JSONObject
        val middleSchoolTimetable = JSONParser().parse(jsonObject["misTimetable"].toString()) as JSONArray?
        if (middleSchoolTimetable != null) {
            val head = (middleSchoolTimetable[0] as JSONObject)["head"] as JSONArray
            val result = (head[1] as JSONObject)["RESULT"] as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val row = (middleSchoolTimetable[1] as JSONObject)["row"] as JSONArray
            for (i in 0 until row.size) {
                val rowObject = row[i] as JSONObject
                val period = rowObject["PERIO"] as String
                val subject = rowObject["ITRT_CNTNT"] as String

                val middleSchoolTimetableResponse = NeisFindMiddleSchoolTimetableResponse(
                    period = period,
                    subject = subject
                )
                middleSchoolTimetableList.add(middleSchoolTimetableResponse)
            }
            return middleSchoolTimetableList
        } else {
            val result = JSONParser().parse(jsonObject["RESULT"].toString()) as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val middleSchoolTimetableResponse = NeisFindMiddleSchoolTimetableResponse(
                period = null,
                subject = null
            )
            return mutableListOf(middleSchoolTimetableResponse)
        }
    }

}
