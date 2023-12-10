package com.project.school.domain.school.adapter.output.feign.config

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients("com.project.school.domain.school.adapter.output.neis")
class FeignConfig {

}