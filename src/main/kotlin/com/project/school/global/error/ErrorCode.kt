package com.project.school.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_ACCOUNT_ID("중복된 id 입니다.", 409),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),
    TOO_MANY_AUTHENTICATION_REQUEST("인증 메세지 요청 5번 초과 한 사용자 입니다.", 429)

}