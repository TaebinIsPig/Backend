package com.project.school.domain.account.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class PasswordNotMatchException: SchoolException(ErrorCode.PASSWORD_NOT_MATCH)