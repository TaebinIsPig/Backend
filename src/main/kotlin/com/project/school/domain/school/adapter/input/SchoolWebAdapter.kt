package com.project.school.domain.school.adapter.input

import com.project.school.domain.school.adapter.input.data.response.MonthSchoolScheduleResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolMealResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolScheduleResponse
import com.project.school.domain.school.adapter.input.data.response.SchoolSearchResponse
import com.project.school.domain.school.adapter.input.mapper.SchoolDataMapper
import com.project.school.domain.school.application.port.input.FindMonthSchoolScheduleUseCase
import com.project.school.domain.school.application.port.input.FindSchoolMealUseCase
import com.project.school.domain.school.application.port.input.FindSchoolScheduleUseCase
import com.project.school.domain.school.application.port.input.SchoolSearchUseCase
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
    private val findMonthSchoolScheduleUseCase: FindMonthSchoolScheduleUseCase
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


}
