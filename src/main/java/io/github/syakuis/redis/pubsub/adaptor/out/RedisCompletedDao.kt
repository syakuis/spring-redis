package io.github.syakuis.redis.pubsub.adaptor.out

import io.github.syakuis.redis.pubsub.domain.FinalCatchEntity
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Repository

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@Repository
class RedisCompletedDao(private val finalCatchRedisTemplate: RedisTemplate<String, FinalCatchEntity>) {
    @Async
    fun save(finalCatchEntity: FinalCatchEntity) {
        finalCatchRedisTemplate.opsForList().rightPush("pub-sub:final-catch:${finalCatchEntity.id}:", finalCatchEntity)
    }
}