package com.project.school.domain.account.domain

import com.project.school.common.annotation.Aggregate

@Aggregate
enum class Authority {

    ROLE_ACCOUNT, ROLE_ADMIN

}