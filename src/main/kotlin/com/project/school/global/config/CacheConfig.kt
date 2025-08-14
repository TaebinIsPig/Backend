package com.project.school.global.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun caffeineConfig(): Caffeine<Any, Any> =
        Caffeine.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS)
            .maximumSize(1000)
            .recordStats()

    @Bean
    fun cacheManager(caffeine: Caffeine<Any, Any>): CacheManager =
        CaffeineCacheManager(
            "elementarySchoolTimetable",
            "middleSchoolTimetable",
            "highSchoolTimetable",
            "monthSchoolSchedule"
        ).apply {
            setCaffeine(caffeine)
        }
}
