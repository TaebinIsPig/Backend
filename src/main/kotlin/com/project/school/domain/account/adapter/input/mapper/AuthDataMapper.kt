package com.project.school.domain.account.adapter.input.mapper

import com.project.school.domain.account.adapter.input.data.request.SignInRequest
import com.project.school.domain.account.adapter.input.data.request.SignUpRequest
import com.project.school.domain.account.adapter.input.data.response.TokenResponse
import com.project.school.domain.account.application.port.input.dto.SignInDto
import com.project.school.domain.account.application.port.input.dto.SignUpDto
import com.project.school.domain.account.application.port.output.dto.TokenDto
import com.project.school.domain.school.adapter.input.mapper.SchoolDataMapper
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AuthDataMapper(
    private val studentNumberDataMapper: StudentNumberDataMapper,
    private val schoolDataMapper: SchoolDataMapper
) {

    infix fun toDto(request: SignUpRequest): SignUpDto =
        SignUpDto(
            id = request.id,
            password = request.password,
            name = request.name,
            studentNumber = studentNumberDataMapper.toDto(request.studentNumber),
            phoneNumber = request.phoneNumber,
            school = schoolDataMapper.toDto(request.school)
        )

    infix fun toDto(request: SignInRequest): SignInDto =
        SignInDto(
            id = request.id,
            password = request.password
        )

    infix fun toResponse(dto: TokenDto): TokenResponse =
        TokenResponse(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken,
            accessTokenExpiredAt = LocalDateTime.now().plusSeconds(dto.accessTokenExpiredAt),
            refreshTokenExpiredAt = LocalDateTime.now().plusSeconds(dto.refreshTokenExpiredAt)
        )

}