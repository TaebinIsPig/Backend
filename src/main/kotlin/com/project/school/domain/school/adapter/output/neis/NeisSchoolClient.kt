package com.project.school.domain.school.adapter.output.neis

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "neisSchoolClient", url = "https://open.neis.go.kr/hub")
interface NeisSchoolClient {

    @GetMapping("/schoolInfo")
    fun schoolSearch(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pIndex") pIndex: Int,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("SCHUL_NM") schoolName: String
    ): String

    @GetMapping("/mealServiceDietInfo")
    fun findSchoolMeal(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pIndex") pIndex: Int,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") educationCode: String,
        @RequestParam("SD_SCHUL_CODE") adminCode: String,
        @RequestParam("MLSV_YMD") date: String
    ): String

    @GetMapping("/SchoolSchedule")
    fun findSchoolSchedule(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pIndex") pIndex: Int,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") educationCode: String,
        @RequestParam("SD_SCHUL_CODE") adminCode: String,
        @RequestParam("AA_YMD") date: String
    ): String

    @GetMapping("/hisTimetable")
    fun findHighSchoolTimeTable(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pIndex") pIndex: Int,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") educationCode: String,
        @RequestParam("SD_SCHUL_CODE") adminCode: String,
        @RequestParam("GRADE") grade: String,
        @RequestParam("CLASS_NM") classNum: String,
        @RequestParam("ALL_TI_YMD") date: String
    ): String

    @GetMapping("/misTimetable")
    fun findMiddleSchoolTimetable(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pIndex") pIndex: Int,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") educationCode: String,
        @RequestParam("SD_SCHUL_CODE") adminCode: String,
        @RequestParam("GRADE") grade: String,
        @RequestParam("CLASS_NM") classNum: String,
        @RequestParam("ALL_TI_YMD") date: String
    ): String

    @GetMapping("/elsTimetable")
    fun findElementarySchoolTimetable(
        @RequestParam("KEY") key: String,
        @RequestParam("Type") type: String,
        @RequestParam("pIndex") pIndex: Int,
        @RequestParam("pSize") pSize: Int,
        @RequestParam("ATPT_OFCDC_SC_CODE") educationCode: String,
        @RequestParam("SD_SCHUL_CODE") adminCode: String,
        @RequestParam("GRADE") grade: String,
        @RequestParam("CLASS_NM") classNum: String,
        @RequestParam("ALL_TI_YMD") date: String
    ): String
}
