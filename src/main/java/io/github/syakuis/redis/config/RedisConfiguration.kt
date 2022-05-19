package io.github.syakuis.redis.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
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
    private val host: String = ""

    @Value("\${spring.redis.port}")
    private val port: Int = 0

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, ByteArray> {
        val redisTemplate: RedisTemplate<String, ByteArray> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()

        return redisTemplate
    }

    @Bean
    fun ticketRedisTemplate(): RedisTemplate<String, ByteArray> {
        val redisTemplate: RedisTemplate<String, ByteArray> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        return redisTemplate
    }
}