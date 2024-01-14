package com.project.school.global.security.config

import com.project.school.global.security.handler.CustomAccessDeniedHandler
import com.project.school.global.security.handler.CustomAuthenticationEntryPoint
import com.project.school.global.security.token.TokenParseAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserAdapter: TokenParseAdapter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .apply(FilterConfig(jwtParserAdapter))
        authorizationHttpRequests(http)
        exceptionHandling(http)
        return http.build()
    }

    private fun authorizationHttpRequests(http: HttpSecurity) {
        http.authorizeHttpRequests()
            .mvcMatchers("/api/v1/auth/**").permitAll()
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/phone-number/{phoneNumber}").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/find/password").permitAll()
            .mvcMatchers(HttpMethod.GET, "/api/v1/school/search").permitAll()
            .anyRequest().permitAll()
    }

    private fun exceptionHandling(http: HttpSecurity) {
        http.exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
    }

}