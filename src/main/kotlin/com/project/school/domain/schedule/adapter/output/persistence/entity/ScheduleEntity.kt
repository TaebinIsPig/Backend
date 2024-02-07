package com.project.school.domain.schedule.adapter.output.persistence.entity

import com.project.school.common.entity.BaseIdxEntity
import com.project.school.domain.account.adapter.output.persistence.entity.AccountEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "schedule")
class ScheduleEntity(
    @Column(name = "schedule_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val date: String,

    @Column(nullable = false, length = 100)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: AccountEntity
): BaseIdxEntity(idx)