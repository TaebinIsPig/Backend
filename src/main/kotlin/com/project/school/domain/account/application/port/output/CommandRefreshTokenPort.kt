package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.RefreshToken

interface CommandRefreshTokenPort {

    fun saveRefreshToken(refreshToken: RefreshToken): String

}