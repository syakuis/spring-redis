package io.github.syakuis.redis.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
class RedisCacheConfiguration(val redisConnectionFactory: RedisConnectionFactory) {

    @Bean
    fun cacheManager(): CacheManager {
        // 모든 캐시에 대한 기본 설정
        val defaultConfiguration: RedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            // 캐시 유효 시간 설정
            .entryTtl(Duration.ZERO)
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
//            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))

        val cacheConfiguration: Map<String, RedisCacheConfiguration> = mapOf(
            "sample" to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ZERO)
        )

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(defaultConfiguration)
            .withInitialCacheConfigurations(cacheConfiguration)
            .build()
    }
}