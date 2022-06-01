package io.github.syakuis.redis.pubsub.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.syakuis.redis.pubsub.domain.FinalCatchEntity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Configuration
class PubSubRedisConfiguration(private val redisConnectionFactory: RedisConnectionFactory, private val objectMapper: ObjectMapper) {
    @Bean
    fun pubSubRedisTemplate(): RedisTemplate<String, ByteArray> {
        val redisTemplate: RedisTemplate<String, ByteArray> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        redisTemplate.keySerializer = StringRedisSerializer()
        return redisTemplate
    }

    @Bean
    fun finalCatchRedisTemplate(): RedisTemplate<String, FinalCatchEntity> {
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(FinalCatchEntity::class.java)
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)

        val redisTemplate: RedisTemplate<String, FinalCatchEntity> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = jackson2JsonRedisSerializer
        return redisTemplate
    }

    @Bean
    fun redisMessageListenerContainer(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
        return container
    }
}