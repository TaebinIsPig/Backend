package com.project.school.domain.account.domain

import com.project.school.common.annotation.RootAggregate
import com.project.school.domain.school.domain.School
import java.util.UUID

@RootAggregate
data class Account(
    val accountIdx: UUID,
    val id: String,
    var password: String,
    val name: String,
    val studentNumber: StudentNumber,
    val phoneNumber: String,
    val school: School,
    val authority: Authority
) {

    fun updateEncodedPassword(encodedNewPassword: String): Account {
        this.password = encodedNewPassword
        return this
    }

}
