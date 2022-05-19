package io.github.syakuis.redis.domain

import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
data class TicketDto(
    override val id: String,
    override val productId: String,
    override val name: String,
    override val createdOn: LocalDateTime,
    override val expiredOn: LocalDateTime,
): Ticket, Serializable