package com.project.school.domain.account.application.event

import com.project.school.domain.account.application.port.output.CommandAuthenticationPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {  }

@Component
class DeleteAuthenticationEventHandler(
    private val commandAuthenticationPort: CommandAuthenticationPort
) {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun deleteAuthentication(deleteAuthenticationEvent: DeleteAuthenticationEvent) {
        log.info("deleteAuthentication is active")

        commandAuthenticationPort.deleteAuthentication(deleteAuthenticationEvent.authentication)
    }

}