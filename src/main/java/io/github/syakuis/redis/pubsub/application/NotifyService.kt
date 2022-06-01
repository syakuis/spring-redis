package io.github.syakuis.redis.pubsub.application

import io.github.syakuis.redis.pubsub.adaptor.out.RedisCompletedDao
import io.github.syakuis.redis.pubsub.domain.FinalCatchEntity
import io.github.syakuis.redis.pubsub.model.NotifyCommand
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Service
class NotifyService(private val finalCatchService: FinalCatchService, private val redisCompletedDao: RedisCompletedDao) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun register(notifyCommand: NotifyCommand) {
        finalCatchService.observable("good")
        for (i: Int in 1..1000) {
            delivery(notifyCommand.message)
        }
    }

    @Async
    fun delivery(message: String) {
        redisCompletedDao.save(FinalCatchEntity("good", 1000))
        log.debug("{} 전달되었습니다.", message)
    }
}