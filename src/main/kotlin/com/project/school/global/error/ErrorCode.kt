package com.project.school.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_ACCOUNT_ID("중복된 아이디 입니다.", 409),
    DUPLICATE_ACCOUNT_PHONE_NUMBER("중복된 전화번호 입니다.", 409),
    ACCOUNT_NOT_FOUND("계정을 찾을 수 없습니다.", 404),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),
    TOO_MANY_AUTHENTICATION_REQUEST("인증 메세지 요청 5번 초과 한 사용자 입니다.", 429),

    // AUTH CODE
    AUTH_CODE_NOT_FOUND("인증 코드를 찾을 수 없습니다.", 404),
    AUTH_CODE_NOT_MATCH("인증 코드가 일치 하지 않습니다.", 400),
    TOO_MANY_AUTH_CODE_REQUEST("인증 코드 확인 요청을 5번 초과한 사용자 입니다.", 429),

    // MESSAGE
    MESSAGE_SEND_FAILED("coolsms 메세지 전송에 실패하였습니다.", 500),

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 401),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 401),

    // FEIGN
    FEIGN_BAD_REQUEST("Feign Bad Request", 400),
    FEIGN_UNAUTHORIZED("Feign Unauthorized", 401),
    FEIGN_FORBIDDEN("Feign Forbidden", 403)
}