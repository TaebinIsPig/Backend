package com.project.school.domain.school.adapter.output.neis

import com.project.school.domain.school.adapter.output.neis.response.NeisSchoolSearchResponse
import com.project.school.domain.school.adapter.output.neis.response.Result
import com.project.school.domain.school.adapter.output.neis.response.SchoolListResponse
import com.project.school.domain.school.application.port.output.SchoolSearchPort
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class NeisSchoolSearchAdapter(
    private val restTemplate: RestTemplate
) : SchoolSearchPort {

    override fun schoolSearch(
        type: String,
        key: String,
        pIndex: Int,
        pSize: Int,
        schoolName: String
    ) : NeisSchoolSearchResponse {
        val url = "https://open.neis.go.kr/hub/schoolInfo"

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("Type", type)
            .queryParam("KEY", key)
            .queryParam("pIndex", pIndex)
            .queryParam("pSize", pSize)
            .queryParam("SCHUL_NM", schoolName)
            .build()

        val headers = org.springframework.http.HttpHeaders()
        val request: HttpEntity<String> = HttpEntity(headers)

        val responseEntity =
            restTemplate.exchange(uri.toString(), HttpMethod.GET, request, String::class.java)

        val schoolList = mutableListOf<SchoolListResponse>()

        val jsonObject = JSONParser().parse(responseEntity.body) as JSONObject
        val schoolInfo = JSONParser().parse(jsonObject["schoolInfo"].toString()) as JSONArray?
        if (schoolInfo != null) {
            val head = (schoolInfo[0] as JSONObject)["head"] as JSONArray
            val listTotalCount = ((head[0] as JSONObject)["list_total_count"] as Long).toInt()
            val result = (head[1] as JSONObject)["RESULT"] as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val row = (schoolInfo[1] as JSONObject)["row"] as JSONArray
            for (i in 0 until row.size) {
                val rowObject = row[i] as JSONObject
                val educationCode = rowObject["ATPT_OFCDC_SC_CODE"] as String
                val adminCode = rowObject["SD_SCHUL_CODE"] as String
                val schoolName = rowObject["SCHUL_NM"] as String
                val schoolType = rowObject["SCHUL_KND_SC_NM"] as String

                val schoolListResponse = SchoolListResponse(
                    educationCode = educationCode,
                    adminCode = adminCode,
                    schoolName = schoolName,
                    schoolType = schoolType
                )
                schoolList.add(schoolListResponse)
            }

            return NeisSchoolSearchResponse(
                listTotalCount = listTotalCount,
                result = Result(
                    code = code,
                    message = message
                ),
                list = schoolList
            )
        } else {
            val result = JSONParser().parse(jsonObject["RESULT"].toString()) as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            return NeisSchoolSearchResponse(
                listTotalCount = 0,
                result = Result(
                    code = code,
                    message = message
                ),
                list = null
            )
        }
    }

}