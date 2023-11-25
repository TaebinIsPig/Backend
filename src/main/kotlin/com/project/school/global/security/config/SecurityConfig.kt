package com.project.school.global.security.config

import com.project.school.global.security.handler.CustomAccessDeniedHandler
import com.project.school.global.security.handler.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
        authorizationHttpRequests(http)
        exceptionHandling(http)
        return http.build()
    }

    private fun authorizationHttpRequests(http: HttpSecurity) {
        http.authorizeHttpRequests()
            .mvcMatchers("/api/v1/auth/**").permitAll()
            .anyRequest().permitAll()
    }

    private fun exceptionHandling(http: HttpSecurity) {
        http.exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
    }

}