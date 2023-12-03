package com.project.school.domain.account.adapter.input

import com.project.school.domain.account.application.port.input.FindAccountIdUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/account")
class AccountWebAdapter(
    private val findAccountIdUseCase: FindAccountIdUseCase
) {

    @GetMapping("/find/id/phone-number/{phoneNumber}")
    fun findAccountId(@PathVariable phoneNumber: String): ResponseEntity<Map<String, String>> =
        findAccountIdUseCase.execute(phoneNumber)
            .let { ResponseEntity.ok(mapOf("id" to it)) }

}