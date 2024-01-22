package com.project.school.domain.account.domain

import com.project.school.common.annotation.RootAggregate
import com.project.school.domain.account.application.port.input.dto.SchoolDto
import com.project.school.domain.account.application.port.input.dto.StudentNumberDto
import com.project.school.domain.school.domain.School
import java.util.UUID

@RootAggregate
data class Account(
    val accountIdx: UUID,
    val id: String,
    var password: String,
    var name: String,
    var studentNumber: StudentNumber,
    var phoneNumber: String,
    var school: School,
    val authority: Authority
) {

    fun updateEncodedPassword(encodedNewPassword: String): Account {
        this.password = encodedNewPassword
        return this
    }

    fun updateProfile(
        name: String,
        phoneNumber: String,
        school: SchoolDto,
        studentNumber: StudentNumberDto
        ): Account {
        this.name = name
        this.phoneNumber = phoneNumber
        this.school.educationCode = school.educationCode
        this.school.adminCode = school.adminCode
        this.school.schoolName = school.schoolName
        this.school.schoolType = school.schoolType
        this.studentNumber.grade = studentNumber.grade
        this.studentNumber.classNum = studentNumber.classNum
        this.studentNumber.number = studentNumber.number
        return this
    }

}
