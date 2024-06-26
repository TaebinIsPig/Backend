package com.project.school.domain.school.adapter.input

import com.project.school.domain.school.adapter.input.data.response.*
import com.project.school.domain.school.adapter.input.mapper.SchoolDataMapper
import com.project.school.domain.school.application.port.input.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/school")
class SchoolWebAdapter(
    private val schoolDataMapper: SchoolDataMapper,
    private val schoolSearchUseCase: SchoolSearchUseCase,
    private val findSchoolMealUseCase: FindSchoolMealUseCase,
    private val findSchoolScheduleUseCase: FindSchoolScheduleUseCase,
    private val findMonthSchoolScheduleUseCase: FindMonthSchoolScheduleUseCase,
    private val findElementarySchoolTimetableUseCase: FindElementarySchoolTimetableUseCase,
    private val findMiddleSchoolTimetableUseCase: FindMiddleSchoolTimetableUseCase,
    private val findHighSchoolTimetableUseCase: FindHighSchoolTimetableUseCase
) {

    @GetMapping("/search")
    fun schoolSearch(@RequestParam page: Int, @RequestParam schoolName: String): ResponseEntity<SchoolSearchResponse> =
        schoolSearchUseCase.execute(page, schoolName)
            .let { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/meals")
    fun findSchoolMeal(@RequestParam date: String): ResponseEntity<List<SchoolMealResponse>> =
        findSchoolMealUseCase.execute(date)
            .map { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/schedule")
    fun findSchoolSchedule(@RequestParam date: String): ResponseEntity<List<SchoolScheduleResponse>> =
        findSchoolScheduleUseCase.execute(date)
            .map { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/schedule/month")
    fun findMonthSchoolSchedule(@RequestParam date: String): ResponseEntity<List<MonthSchoolScheduleResponse>> =
        findMonthSchoolScheduleUseCase.execute(date)
            .map { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/timetable/elementary")
    fun findElementSchoolTimetable(@RequestParam grade: String, @RequestParam classNum: String, @RequestParam date: String): ResponseEntity<List<ElementarySchoolTimetableResponse>> =
        findElementarySchoolTimetableUseCase.execute(grade, classNum, date)
            .map { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/timetable/middle")
    fun findMiddleSchoolTimetable(@RequestParam grade: String, @RequestParam classNum: String, @RequestParam date: String): ResponseEntity<List<MiddleSchoolTimetableResponse>> =
        findMiddleSchoolTimetableUseCase.execute(grade, classNum, date)
            .map { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/timetable/high")
    fun findHighSchoolTimetable(@RequestParam grade: String, @RequestParam classNum: String, @RequestParam date: String): ResponseEntity<List<HighSchoolTimetableResponse>> =
        findHighSchoolTimetableUseCase.execute(grade, classNum, date)
            .map { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}
