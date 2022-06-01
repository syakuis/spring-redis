package io.github.syakuis.redis.pubsub.adaptor.`in`

import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Component

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Component
class PubSubRedisConsumer(private val redisMessageListenerContainer: RedisMessageListenerContainer) : MessageListener {
    private val log = LoggerFactory.getLogger(javaClass)

    fun createNotify(id: String) {
        redisMessageListenerContainer.addMessageListener(this, ChannelTopic(id))
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val id = String(message.channel)

        log.debug("{} : {}", id, String(message.body))

        log.debug(pattern?.toString())
    }
}