package com.project.school.domain.school.adapter.output.feign.error.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class FeignUnAuthorizedException: SchoolException(ErrorCode.FEIGN_UNAUTHORIZED)