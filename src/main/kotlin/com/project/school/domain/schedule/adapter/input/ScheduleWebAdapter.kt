package com.project.school.domain.schedule.adapter.input

import com.project.school.domain.schedule.adapter.input.data.request.CreateScheduleRequest
import com.project.school.domain.schedule.adapter.input.mapper.ScheduleDataMapper
import com.project.school.domain.schedule.application.port.input.CreateScheduleUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/schedule")
class ScheduleWebAdapter(
    private val createScheduleUseCase: CreateScheduleUseCase,
    private val scheduleDataMapper: ScheduleDataMapper
) {

    @PostMapping("/create")
    fun createSchedule(@RequestBody request: CreateScheduleRequest): ResponseEntity<Void> =
        createScheduleUseCase.execute(scheduleDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

}