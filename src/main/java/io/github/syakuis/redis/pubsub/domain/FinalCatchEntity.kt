package io.github.syakuis.redis.pubsub.domain

import java.time.LocalDateTime

class FinalCatchEntity(val id: Any, val count: Int) {
    val registeredOn: LocalDateTime = LocalDateTime.now()
}
