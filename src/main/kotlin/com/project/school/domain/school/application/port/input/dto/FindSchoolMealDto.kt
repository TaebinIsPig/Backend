package com.project.school.domain.school.application.port.input.dto

data class FindSchoolMealDto(
    val educationCode: String,
    val adminCode: String,
    val date: String
)
