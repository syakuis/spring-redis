package io.github.syakuis.redis.pubsub.adaptor.out

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Component
class PubSubRedisProducer(private val pubSubRedisTemplate: RedisTemplate<String, ByteArray>) {

    fun notify(id: String, message: String) {
        pubSubRedisTemplate.convertAndSend(id, message)
    }
}