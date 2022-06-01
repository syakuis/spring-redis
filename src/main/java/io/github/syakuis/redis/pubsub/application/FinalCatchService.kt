package io.github.syakuis.redis.pubsub.application

import io.github.syakuis.redis.pubsub.domain.FinalCatchEntity
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service
import java.time.Duration

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Service
class FinalCatchService(private val finalCatchRedisTemplate: RedisTemplate<String, FinalCatchEntity>, private val redisMessageListenerContainer: RedisMessageListenerContainer) : MessageListener {
    private val log = LoggerFactory.getLogger(javaClass)

    fun observable(id: String) {
        finalCatchRedisTemplate.expire("pub-sub:final-catch:${id}:", Duration.ofDays(1))
        redisMessageListenerContainer.addMessageListener(this, ChannelTopic("pub-sub:final-catch:${id}:"))
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val id = String(message.channel)

        log.debug("{} : {}", id, message.body)

        log.debug(pattern?.toString())
    }
}