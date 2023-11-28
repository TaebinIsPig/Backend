package com.project.school.domain.account.adapter.output.message

import com.project.school.domain.account.adapter.output.message.exception.MessageSendFailedException
import com.project.school.domain.account.adapter.output.message.properties.CoolSmsProperties
import com.project.school.domain.account.application.port.output.SendMessagePort
import mu.KotlinLogging
import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class CoolSmsAdapter(
    private val coolSmsProperties: CoolSmsProperties,
): SendMessagePort {

    override fun sendMessage(phoneNumber: String, authCode: Int) {

        val messageService: DefaultMessageService =
            initialize(coolSmsProperties.access, coolSmsProperties.secret, "https://api.coolsms.co.kr")

        val coolsms = Message(
            from = coolSmsProperties.phoneNumber,
            to = phoneNumber,
            text = "School 인증번호는 [ $authCode ] 입니다."
        )

        try {
            messageService.send(coolsms)
        } catch (e: Exception) {
            e.printStackTrace()
            log.error("coolsms message send failed")
            throw MessageSendFailedException()
        }
    }

}