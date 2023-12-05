package com.project.school.domain.account.adapter.input

import com.project.school.domain.account.adapter.input.data.request.SignInRequest
import com.project.school.domain.account.adapter.input.data.request.SignUpRequest
import com.project.school.domain.account.adapter.input.data.response.TokenResponse
import com.project.school.domain.account.adapter.input.mapper.AuthDataMapper
import com.project.school.domain.account.application.port.input.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthWebAdapter(
    private val authDataMapper: AuthDataMapper,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val verifyAuthCodeUseCase: VerifyAuthCodeUseCase
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        signUpUseCase.execute(authDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        signInUseCase.execute(authDataMapper toDto request)
            .let { authDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/reissue")
    fun reissueToken(@RequestHeader refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { authDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PostMapping("/send/phone-number/{phoneNumber}")
    fun sendAuthCode(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        sendAuthCodeUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("/auth-code/{authCode}/phone-number/{phoneNumber}")
    fun verifyAuthCode(@PathVariable authCode: Int, @PathVariable phoneNumber: String): ResponseEntity<Void> =
        verifyAuthCodeUseCase.execute(authCode, phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}