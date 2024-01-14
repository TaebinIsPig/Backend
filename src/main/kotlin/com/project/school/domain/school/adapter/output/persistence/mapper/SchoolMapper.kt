package com.project.school.domain.school.adapter.output.persistence.mapper

import com.project.school.domain.school.adapter.output.persistence.entity.SchoolEntity
import com.project.school.domain.school.domain.School
import org.springframework.stereotype.Component

@Component
class SchoolMapper {

    fun toEntity(domain: School): SchoolEntity =
        SchoolEntity(
            educationCode = domain.educationCode,
            adminCode = domain.adminCode,
            schoolName = domain.schoolName,
            schoolType = domain.schoolType
        )

    fun toDomain(entity: SchoolEntity): School =
        School(
            educationCode = entity.educationCode,
            adminCode = entity.adminCode,
            schoolName = entity.schoolName,
            schoolType = entity.schoolType
        )

}