package com.project.school.domain.school.adapter.output.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class SchoolEntity(
    @Column(nullable = false)
    val educationCode: String,

    @Column(nullable = false)
    val adminCode: String,

    @Column(nullable = false)
    val schoolName: String,

    @Column(nullable = false)
    val schoolType: String
)