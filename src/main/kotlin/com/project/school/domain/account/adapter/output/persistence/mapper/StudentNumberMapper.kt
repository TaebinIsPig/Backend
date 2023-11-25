package com.project.school.domain.account.adapter.output.persistence.mapper

import com.project.school.domain.account.adapter.output.persistence.entity.StudentNumberEntity
import com.project.school.domain.account.domain.StudentNumber
import org.springframework.stereotype.Component

@Component
class StudentNumberMapper {

    fun toEntity(domain: StudentNumber): StudentNumberEntity =
        StudentNumberEntity(
            grade = domain.grade,
            classNum = domain.classNum,
            number = domain.number
        )

}