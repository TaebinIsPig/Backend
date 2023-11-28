package com.project.school.domain.account.adapter.output.message.exception

import com.project.school.global.error.ErrorCode
import com.project.school.global.error.exception.SchoolException

class MessageSendFailedException: SchoolException(ErrorCode.MESSAGE_SEND_FAILED)