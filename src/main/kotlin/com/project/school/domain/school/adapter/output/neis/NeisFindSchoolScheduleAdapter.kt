package com.project.school.domain.school.adapter.output.neis

import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolScheduleResponse
import com.project.school.domain.school.adapter.output.neis.response.Result
import com.project.school.domain.school.application.port.output.FindSchoolSchedulePort
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
class NeisFindSchoolScheduleAdapter(
    private val restTemplate: RestTemplate
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
        val url = "https://open.neis.go.kr/hub/SchoolSchedule"

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("Key", key)
            .queryParam("Type", type)
            .queryParam("pIndex", pIndex)
            .queryParam("pSize", pSize)
            .queryParam("ATPT_OFCDC_SC_CODE", educationCode)
            .queryParam("SD_SCHUL_CODE", adminCode)
            .queryParam("AA_YMD", date)
            .build()

        val headers = HttpHeaders()
        val request: HttpEntity<String> = HttpEntity(headers)

        val responseEntity =
            restTemplate.exchange(uri.toString(), HttpMethod.GET, request, String::class.java)

        val schoolScheduleList = mutableListOf<NeisFindSchoolScheduleResponse>()

        val jsonObject = JSONParser().parse(responseEntity.body) as JSONObject
        val schoolSchedule = JSONParser().parse(jsonObject["SchoolSchedule"].toString()) as JSONArray?
        if (schoolSchedule != null) {
            val head = (schoolSchedule[0] as JSONObject)["head"] as JSONArray
            val result = (head[1] as JSONObject)["RESULT"] as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val row = (schoolSchedule[1] as JSONObject)["row"] as JSONArray
            for (i in 0 until row.size) {
                val rowObject = row[i] as JSONObject
                val eventDate = rowObject["AA_YMD"] as String
                val eventName = rowObject["EVENT_NM"] as String

                val schoolScheduleResponse = NeisFindSchoolScheduleResponse(
                    eventDate = eventDate,
                    eventName = eventName,
                    result = Result(
                        code = code,
                        message = message
                    )
                )
                schoolScheduleList.add(schoolScheduleResponse)
            }
            return schoolScheduleList
        } else {
            val result = JSONParser().parse(jsonObject["RESULT"].toString()) as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val schoolScheduleResponse = NeisFindSchoolScheduleResponse(
                eventDate = null,
                eventName = null,
                result = Result(
                    code = code,
                    message = message
                )
            )
            return mutableListOf(schoolScheduleResponse)
        }
    }
}