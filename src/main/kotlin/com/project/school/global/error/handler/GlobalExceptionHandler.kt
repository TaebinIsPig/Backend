package com.project.school.global.error.handler

import com.project.school.global.error.exception.SchoolException
import com.project.school.global.error.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(SchoolException::class)
    fun handler(e: SchoolException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.errorCode.message, e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.bindingResult.allErrors[0].defaultMessage, HttpStatus.BAD_REQUEST.value()),
            HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value())
        )

}