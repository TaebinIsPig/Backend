package com.project.school.domain.account.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class AuthenticationNotFoundException: SchoolException(ErrorCode.AUTHENTICATION_NOT_FOUND)