package io.github.syakuis.redis.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-11
 */
@Configuration
class RedisConfiguration {

    @Value("\${spring.redis.host}")
    val host: String = ""

    @Value("\${spring.redis.port}")
    val port: Int = 0

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate: RedisTemplate<String, Any> = RedisTemplate()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}