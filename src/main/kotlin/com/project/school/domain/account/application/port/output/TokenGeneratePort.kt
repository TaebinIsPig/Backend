package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.application.port.output.dto.TokenDto
import com.project.school.domain.account.domain.Authority
import java.util.UUID

interface TokenGeneratePort {

    fun generateToken(accountIdx: UUID, authority: Authority): TokenDto

}