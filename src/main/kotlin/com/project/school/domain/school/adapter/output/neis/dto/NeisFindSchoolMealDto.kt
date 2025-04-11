package com.project.school.domain.school.adapter.output.neis.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NeisFindSchoolMealDto(
    @JsonProperty("mealServiceDietInfo")
    val schoolMeal: List<SchoolMeal>
)
data class SchoolMeal(
    val head: List<SchoolMealHead>? = null,
    val row: List<SchoolMealRow>? = null
)

data class SchoolMealHead(
    @JsonProperty("RESULT")
    val result: SchoolMealResult? = null
)

data class SchoolMealResult(
    @JsonProperty("CODE")
    val code: String,
    @JsonProperty("MESSAGE")
    val message: String
)

data class SchoolMealRow(
    @JsonProperty("MMEAL_SC_NM")
    val mealType: String,
    @JsonProperty("MLSV_YMD")
    val mealDate: String,
    @JsonProperty("DDISH_NM")
    val food: String,
    @JsonProperty("CAL_INFO")
    val calorie: String,
)
