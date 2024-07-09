package com.project.school.domain.schedule.adapter.input

import com.project.school.domain.schedule.adapter.input.data.request.CreateScheduleRequest
import com.project.school.domain.schedule.adapter.input.data.request.UpdateScheduleRequest
import com.project.school.domain.schedule.adapter.input.data.response.FindScheduleResponse
import com.project.school.domain.schedule.adapter.input.mapper.ScheduleDataMapper
import com.project.school.domain.schedule.application.port.input.CreateScheduleUseCase
import com.project.school.domain.schedule.application.port.input.DeleteScheduleUseCase
import com.project.school.domain.schedule.application.port.input.FindScheduleUseCase
import com.project.school.domain.schedule.application.port.input.UpdateScheduleUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/schedule")
class ScheduleWebAdapter(
    private val createScheduleUseCase: CreateScheduleUseCase,
    private val updateScheduleUseCase: UpdateScheduleUseCase,
    private val deleteScheduleUseCase: DeleteScheduleUseCase,
    private val findScheduleUseCase: FindScheduleUseCase,
    private val scheduleDataMapper: ScheduleDataMapper
) {

    @PostMapping("/create")
    fun createSchedule(@RequestBody request: CreateScheduleRequest): ResponseEntity<Void> =
        createScheduleUseCase.execute(scheduleDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PatchMapping("/update/{idx}")
    fun updateSchedule(@PathVariable idx: Long, @RequestBody request: UpdateScheduleRequest): ResponseEntity<Void> =
        updateScheduleUseCase.execute(idx, scheduleDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @DeleteMapping("/delete/{idx}")
    fun deleteSchedule(@PathVariable idx: Long): ResponseEntity<Void> =
        deleteScheduleUseCase.execute(idx)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @GetMapping
    fun findSchedule(@RequestParam date: String): ResponseEntity<FindScheduleResponse> =
        findScheduleUseCase.execute(date)
            .let { ResponseEntity.ok(it) }

}
