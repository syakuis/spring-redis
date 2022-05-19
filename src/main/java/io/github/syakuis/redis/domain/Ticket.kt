package io.github.syakuis.redis.domain

import java.time.LocalDateTime

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
interface Ticket {
    val id: String
    val productId: String
    val name: String
    val createdOn: LocalDateTime
    val expiredOn: LocalDateTime
}