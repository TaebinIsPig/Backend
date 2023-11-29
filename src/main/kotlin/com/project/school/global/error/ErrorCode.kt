package com.project.school.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_ACCOUNT_ID("중복된 id 입니다.", 409),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),
    TOO_MANY_AUTHENTICATION_REQUEST("인증 메세지 요청 5번 초과 한 사용자 입니다.", 429),

    // AUTH CODE
    AUTH_CODE_NOT_FOUND("인증 코드를 찾을 수 없습니다.", 404),
    AUTH_CODE_NOT_MATCH("인증 코드가 일치 하지 않습니다.", 400),
    TOO_MANY_AUTH_CODE_REQUEST("인증 코드 확인 요청을 5번 초과한 사용자 입니다.", 429),

    // MESSAGE
    MESSAGE_SEND_FAILED("coolsms 메세지 전송에 실패하였습니다.", 500)

}