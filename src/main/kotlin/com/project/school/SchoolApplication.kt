package com.project.school

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SchoolApplication

fun main(args: Array<String>) {
    runApplication<SchoolApplication>(*args)
}
