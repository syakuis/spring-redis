package io.github.syakuis.redis.log.domain

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-11
 */
interface LogRepository {
    fun save(log: Log)
    fun findById(id: Long): Log
    fun findAll(): List<Log>
}