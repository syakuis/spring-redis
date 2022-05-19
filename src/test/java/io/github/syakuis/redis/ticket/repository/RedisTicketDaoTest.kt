package io.github.syakuis.redis.ticket.repository

import io.github.syakuis.redis.ticket.domain.TicketDto
import io.kotest.core.spec.style.BehaviorSpec
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */

@SpringBootTest
internal class RedisTicketDaoTest: BehaviorSpec() {
    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var redisTicketDao: RedisTicketDao

    init {
        Given("ticket 정보를 만든다") {
            val current = LocalDateTime.now()
            val ticketDto = TicketDto(
                id = UUID.randomUUID().toString(),
                productId = "A",
                name = "뇸",
                createdOn = current,
                expiredOn = current.plusDays(2)
            )

            When("저장한다") {
                redisTicketDao.save(ticketDto)
                log.debug("{}", ticketDto)
                log.debug("{}", redisTicketDao.findAll())
            }
        }
    }
}