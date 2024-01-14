package com.project.school.domain.school.application.service

import com.project.school.common.annotation.ServiceWithReadOnlyTransaction
import com.project.school.domain.school.adapter.output.neis.properties.NeisProperties
import com.project.school.domain.school.application.port.input.SchoolSearchUseCase
import com.project.school.domain.school.application.port.input.dto.SchoolList
import com.project.school.domain.school.application.port.input.dto.SchoolSearchDto
import com.project.school.domain.school.application.port.output.SchoolSearchPort

@ServiceWithReadOnlyTransaction
class SchoolSearchService(
    private val neisProperties: NeisProperties,
    private val schoolSearchPort: SchoolSearchPort
) : SchoolSearchUseCase {

    override fun execute(page: Int, schoolName: String): SchoolSearchDto {
        val schoolInfo = schoolSearchPort.schoolSearch("json", neisProperties.authKey, page, 20, schoolName)
        val isLast = schoolInfo.listTotalCount?.minus(20 * page)!! <= 0
        return SchoolSearchDto(
            isLast = isLast,
            schoolList = schoolInfo.list?.map { schoolListResponse ->
                SchoolList(
                    educationCode = schoolListResponse.educationCode,
                    adminCode = schoolListResponse.adminCode,
                    schoolName = schoolListResponse.schoolName,
                    schoolType = schoolListResponse.schoolType
                )
            }
        )
    }

}