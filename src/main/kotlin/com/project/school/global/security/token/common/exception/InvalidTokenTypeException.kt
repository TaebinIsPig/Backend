package com.project.school.global.security.token.common.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class InvalidTokenTypeException: SchoolException(ErrorCode.INVALID_TOKEN_TYPE)