package com.project.school.domain.school.application.port.output.cache

interface CachePort {
    fun <T> get(cacheName: String, key: String, type: Class<T>): T?
    fun put(cacheName: String, key: String, value: Any)
}
