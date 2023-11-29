package com.project.school.domain.account.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class AuthCodeNotFoundException: SchoolException(ErrorCode.AUTH_CODE_NOT_FOUND)