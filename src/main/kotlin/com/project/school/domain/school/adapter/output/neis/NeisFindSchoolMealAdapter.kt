package com.project.school.domain.school.adapter.output.neis

import com.project.school.domain.school.adapter.output.neis.response.NeisFindSchoolMealResponse
import com.project.school.domain.school.adapter.output.neis.response.Result
import com.project.school.domain.school.application.port.output.FindSchoolMealPort
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class NeisFindSchoolMealAdapter(
    val restTemplate: RestTemplate
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
        val url = "https://open.neis.go.kr/hub/mealServiceDietInfo"

        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("KEY", key)
            .queryParam("Type", type)
            .queryParam("pIndex", pIndex)
            .queryParam("pSize", pSize)
            .queryParam("ATPT_OFCDC_SC_CODE", educationCode)
            .queryParam("SD_SCHUL_CODE", adminCode)
            .queryParam("MLSV_YMD", date)
            .build()

        val headers = org.springframework.http.HttpHeaders()
        val request: HttpEntity<String> = HttpEntity(headers)

        val responseEntity =
            restTemplate.exchange(uri.toString(), HttpMethod.GET, request, String::class.java)

        val schoolMealList = mutableListOf<NeisFindSchoolMealResponse>()

        val jsonObject = JSONParser().parse(responseEntity.body) as JSONObject
        val mealServiceDietInfo = JSONParser().parse(jsonObject["mealServiceDietInfo"].toString()) as JSONArray?
        if (mealServiceDietInfo != null) {
            val head = (mealServiceDietInfo[0] as JSONObject)["head"] as JSONArray
            val result = (head[1] as JSONObject)["RESULT"] as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val row = (mealServiceDietInfo[1] as JSONObject)["row"] as JSONArray
            for (i in 0 until row.size) {
                val rowObject = row[i] as JSONObject
                val mealType = rowObject["MMEAL_SC_NM"] as String
                val mealDate = rowObject["MLSV_YMD"] as String
                val food = rowObject["DDISH_NM"] as String
                val calorie = rowObject["CAL_INFO"] as String

                val schoolMealResponse = NeisFindSchoolMealResponse(
                    mealType = mealType,
                    mealDate = mealDate,
                    food = food.split("<br/>"),
                    calorie = calorie,
                    result = Result(
                        code = code,
                        message = message
                    )
                )
                schoolMealList.add(schoolMealResponse)
            }
            return schoolMealList
        } else {
            val result = JSONParser().parse(jsonObject["RESULT"].toString()) as JSONObject
            val code = result["CODE"] as String
            val message = result["MESSAGE"] as String
            val schoolMealResponse = NeisFindSchoolMealResponse(
                mealType = null,
                mealDate = null,
                food = null,
                calorie = null,
                result = Result(
                    code = code,
                    message = message
                )
            )
            return mutableListOf(schoolMealResponse)
        }

    }

}