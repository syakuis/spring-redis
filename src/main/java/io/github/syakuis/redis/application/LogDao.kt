package io.github.syakuis.redis.application

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.syakuis.redis.domain.Log
import io.github.syakuis.redis.domain.LogRepository
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-11
 */
@Service
class LogDao(
    val objectMapper: ObjectMapper,
    val redisTemplate: RedisTemplate<String, ByteArray>) : LogRepository {
    override fun save(log: Log) {
        val logs: ListOperations<String, ByteArray> = redisTemplate.opsForList()
        logs.rightPush("log", objectMapper.writeValueAsBytes(log))
    }

    override fun findById(id: Long): Log {
        val logs: ListOperations<String, ByteArray> = redisTemplate.opsForList()
        return objectMapper.readValue(logs.index("log", id), Log::class.java)
    }

    override fun findAll(): List<Log> {
        val logs: ListOperations<String, ByteArray> = redisTemplate.opsForList()
        val size = logs.size("log")?: 0
        if (size == 0L) {
            return listOf()
        }

        val data = logs.range("log", 0, size)?: listOf()
        return data.map{ it -> objectMapper.readValue(it, Log::class.java) }
    }
}