package io.github.syakuis.redis.pubsub.model

import java.time.LocalDateTime

data class NotifyCommand(val message: String) {
    val registeredOn: LocalDateTime = LocalDateTime.now()
}
