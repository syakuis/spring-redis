package io.github.syakuis.redis.pubsub.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Configuration
class PubSubRedisConfiguration(private val redisConnectionFactory: RedisConnectionFactory) {
    @Bean
    fun pubSubRedisTemplate(): RedisTemplate<String, ByteArray> {
        val redisTemplate: RedisTemplate<String, ByteArray> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        redisTemplate.keySerializer = StringRedisSerializer()
        return redisTemplate
    }

    @Bean
    fun redisMessageListenerContainer(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
        return container
    }
}