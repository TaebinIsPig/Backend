package com.project.school.domain.account.adapter.output.persistence.entity

import com.project.school.common.entity.BaseUUIDEntity
import com.project.school.domain.account.domain.Authority
import com.project.school.domain.school.adapter.output.persistence.entity.SchoolEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
class AccountEntity(
    @Column(name = "account_idx")
    override val accountIdx: UUID,

    @Column(nullable = false, length = 20)
    val id: String,

    @Column(nullable = false, length = 60)
    val password: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Embedded
    val studentNumber: StudentNumberEntity,

    @Column(nullable = false, length = 15)
    val phoneNumber: String,

    @Embedded
    val school: SchoolEntity,

    @Enumerated(EnumType.STRING)
    val authority: Authority
): BaseUUIDEntity(accountIdx)