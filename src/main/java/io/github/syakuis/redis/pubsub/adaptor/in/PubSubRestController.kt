package io.github.syakuis.redis.pubsub.adaptor.`in`

import io.github.syakuis.redis.pubsub.adaptor.out.PubSubRedisProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@RestController
class PubSubRestController(private val pubSubRedisProducer: PubSubRedisProducer, private val pubSubRedisConsumer: PubSubRedisConsumer) {
    @PostMapping("/pub-sub/v1/alert-notifies")
    fun alert() {
        pubSubRedisConsumer.createNotify("pub-sub:notify:")
    }

    @PostMapping("/pub-sub/v1/notifies")
    fun register(@RequestBody message: String) {
        pubSubRedisProducer.notify("pub-sub:notify:", message)
    }
}