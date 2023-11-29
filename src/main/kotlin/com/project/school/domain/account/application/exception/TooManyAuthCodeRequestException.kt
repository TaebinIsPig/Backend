package com.project.school.domain.account.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class TooManyAuthCodeRequestException: SchoolException(ErrorCode.TOO_MANY_AUTH_CODE_REQUEST)