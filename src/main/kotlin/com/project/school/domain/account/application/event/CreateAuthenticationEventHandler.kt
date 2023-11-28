package com.project.school.domain.account.application.event

import com.project.school.domain.account.application.port.output.CommandAuthenticationPort
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CreateAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @EventListener
    fun createAuthentication(createAuthenticationEvent: CreateAuthenticationEvent) {
        log.info("createAuthenticationEvent is active")

        commandAuthenticationPort.saveAuthentication(createAuthenticationEvent.authentication)
    }

}