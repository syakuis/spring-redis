package io.github.syakuis.redis.ticket.port.out

import io.github.syakuis.redis.domain.Ticket

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
interface TicketDaoPort {
    fun save(ticket: Ticket): Ticket
    fun delete(id: String)
    fun findAll(): List<Ticket>
    fun findByProductId(productId: String): List<Ticket>
    fun findById(id: String): Ticket?
    fun count(): Long
    fun countByProductId(productId: String): Long
}