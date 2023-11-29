package com.project.school.domain.account.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class AccountNotFoundException: SchoolException(ErrorCode.ACCOUNT_NOT_FOUND)