package com.project.school.global.error.exception

import com.project.school.global.error.ErrorCode

open class SchoolException(val errorCode: ErrorCode): RuntimeException(errorCode.message)