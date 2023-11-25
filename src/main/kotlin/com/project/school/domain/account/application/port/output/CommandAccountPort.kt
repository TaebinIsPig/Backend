package com.project.school.domain.account.application.port.output

import com.project.school.domain.account.domain.Account
import java.util.UUID

interface CommandAccountPort {

    fun saveAccount(account: Account): UUID

}