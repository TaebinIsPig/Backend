package com.project.school.global.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        val defaultBuilder = Caffeine.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS)
            .maximumSize(10_000)
            .recordStats()

        val scheduleBuilder = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .recordStats()

        val caches = listOf(
            CaffeineCache("elementarySchoolTimetable", defaultBuilder.build()),
            CaffeineCache("middleSchoolTimetable",     defaultBuilder.build()),
            CaffeineCache("highSchoolTimetable",       defaultBuilder.build()),
            CaffeineCache("monthSchoolSchedule",       defaultBuilder.build()),
            CaffeineCache("schoolSchedule",            defaultBuilder.build()),
            CaffeineCache("schoolMeal",                defaultBuilder.build()),
            CaffeineCache("schedule",                  scheduleBuilder.build())
        )

        return SimpleCacheManager().apply { setCaches(caches) }
    }
}
