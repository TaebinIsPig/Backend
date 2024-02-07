package com.project.school.common.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseIdxEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val idx: Long
)