package com.project.school.global.security.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler: AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN)
    }

}