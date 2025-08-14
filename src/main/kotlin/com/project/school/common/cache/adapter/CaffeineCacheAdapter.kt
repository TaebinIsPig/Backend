package com.project.school.common.cache.adapter

import com.project.school.common.cache.port.CachePort
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component

@Component
class CaffeineCacheAdapter(
    private val cacheManager: CacheManager
): CachePort {

    override fun <T> get(cacheName: String, key: String, type: Class<T>): T? {
        return cacheManager.getCache(cacheName)?.get(key, type)
    }

    override fun put(cacheName: String, key: String, value: Any) {
        cacheManager.getCache(cacheName)?.put(key, value)
    }
}
