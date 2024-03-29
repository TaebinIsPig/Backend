package com.project.school.domain.account.adapter.input

import com.project.school.domain.account.adapter.input.data.request.FindAccountPasswordRequest
import com.project.school.domain.account.adapter.input.data.request.UpdateAccountProfileRequest
import com.project.school.domain.account.adapter.input.data.response.ProfileResponse
import com.project.school.domain.account.adapter.input.mapper.AccountDataMapper
import com.project.school.domain.account.application.port.input.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/account")
class AccountWebAdapter(
    private val accountDataMapper: AccountDataMapper,
    private val findAccountIdUseCase: FindAccountIdUseCase,
    private val findAccountPasswordUseCase: FindAccountPasswordUseCase,
    private val findAccountProfileUseCase: FindAccountProfileUseCase,
    private val accountWithdrawUseCase: AccountWithdrawUseCase,
    private val updateAccountProfileUseCase: UpdateAccountProfileUseCase
) {

    @GetMapping("/find/id/phone-number/{phoneNumber}")
    fun findAccountId(@PathVariable phoneNumber: String): ResponseEntity<Map<String, String>> =
        findAccountIdUseCase.execute(phoneNumber)
            .let { ResponseEntity.ok(mapOf("id" to it)) }

    @PatchMapping("/find/password")
    fun findAccountPassword(@RequestBody request: FindAccountPasswordRequest): ResponseEntity<Void> =
        findAccountPasswordUseCase.execute(accountDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @GetMapping("/profile")
    fun findAccountProfile(): ResponseEntity<ProfileResponse> =
        findAccountProfileUseCase.execute()
            .let { accountDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/withdraw/phone-number/{phoneNumber}")
    fun accountWithdraw(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        accountWithdrawUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/profile")
    fun updateAccountProfile(@RequestBody request: UpdateAccountProfileRequest): ResponseEntity<Void> =
        updateAccountProfileUseCase.execute(accountDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}