package com.project.school.domain.account.adapter.output.persistence.entity

import com.project.school.common.entity.BaseUUIDEntity
import com.project.school.domain.account.domain.Authority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
class AccountEntity(
    @Column(name = "account_idx")
    override val idx: UUID,

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

    @Column(nullable = false)
    val school: String,

    @Enumerated(EnumType.STRING)
    val authority: Authority
): BaseUUIDEntity(idx)