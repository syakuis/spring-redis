package io.github.syakuis.redis.log.domain

import java.time.LocalDateTime

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-11
 */
class Log(
    val message: String,
    var registeredOn: LocalDateTime = LocalDateTime.now()
) {

}