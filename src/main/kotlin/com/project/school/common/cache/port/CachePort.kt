package com.project.school.common.cache.port

interface CachePort {
    fun <T> get(cacheName: String, key: String, type: Class<T>): T?
    fun put(cacheName: String, key: String, value: Any)
    fun evict(cacheName: String, key: String)
}
