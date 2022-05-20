package io.github.syakuis.redis.ticket.repository

import io.github.syakuis.redis.domain.Ticket
import io.github.syakuis.redis.ticket.port.out.TicketDaoPort
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
@Repository
class RedisTicketDao(val ticketRedisTemplate: RedisTemplate<String, ByteArray>) : TicketDaoPort {
    companion object {
        private const val KEY = "ticket:"
    }

    private fun keyGen(productId: String): String {
        return KEY
    }

    override fun save(ticket: Ticket): Ticket {
        ticketRedisTemplate.opsForHash<String, Ticket>()
            .put(keyGen(ticket.productId), ticket.productId, ticket)

        return ticket
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Ticket> {
        return ticketRedisTemplate.opsForHash<String, Ticket>().values(keyGen("*"))
    }

    override fun findByProductId(productId: String): List<Ticket> {
        return listOf()
    }

    override fun findById(id: String): Ticket? {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun countByProductId(productId: String): Long {
        TODO("Not yet implemented")
    }
}