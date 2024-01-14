package com.project.school.domain.school.adapter.input

import com.project.school.domain.school.adapter.input.data.response.SchoolSearchResponse
import com.project.school.domain.school.adapter.input.mapper.SchoolDataMapper
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
    private val schoolSearchUseCase: SchoolSearchUseCase
) {

    @GetMapping("/search")
    fun schoolSearch(@RequestParam page: Int, @RequestParam schoolName: String): ResponseEntity<SchoolSearchResponse> =
        schoolSearchUseCase.execute(page, schoolName)
            .let { schoolDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}