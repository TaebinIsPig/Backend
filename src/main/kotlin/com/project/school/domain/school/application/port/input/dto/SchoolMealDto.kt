package com.project.school.domain.school.application.port.input.dto

data class SchoolMealDto(
    val mealType: String?,
    val mealDate: String?,
    val food: List<String>?,
    val calorie: String?
)