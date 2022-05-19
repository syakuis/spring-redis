package io.github.syakuis.redis.log.application

import io.github.syakuis.redis.log.domain.Log
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-11
 */
@Service
@Transactional
class LogService (val logDao: LogDao) {
    fun register() {
        logDao.save(Log("good"))
    }

    fun list(): List<Log> {
        return logDao.findAll()
    }
}