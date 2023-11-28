package com.project.school.domain.account.adapter.input

import com.project.school.domain.account.adapter.input.data.request.SignUpRequest
import com.project.school.domain.account.adapter.input.mapper.AuthDataMapper
import com.project.school.domain.account.application.port.input.SendAuthCodeUseCase
import com.project.school.domain.account.application.port.input.SignUpUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthWebAdapter(
    private val authDataMapper: AuthDataMapper,
    private val signUpUseCase: SignUpUseCase,
    private val sendAuthCodeUseCase: SendAuthCodeUseCase
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        signUpUseCase.execute(authDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/send/phone-number/{phoneNumber}")
    fun sendAuthCode(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        sendAuthCodeUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}