package com.project.school.domain.account.application.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class DuplicatedAccountPhoneNumberException: SchoolException(ErrorCode.DUPLICATE_ACCOUNT_PHONE_NUMBER)