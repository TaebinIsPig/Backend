package com.project.school.domain.school.adapter.output.neis.response

data class NeisFindSchoolMealResponse(
    val mealType: String?,
    val mealDate : String?,
    val food: List<String>?,
    val calorie: String?,
    val result: Result
) {
    constructor() : this(
        mealType = "",
        mealDate = "",
        food = listOf(),
        calorie = "",
        result = Result(
            code = "",
            message = ""
        )
    )
}
