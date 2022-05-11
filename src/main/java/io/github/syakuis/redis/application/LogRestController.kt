package io.github.syakuis.redis.application

import io.github.syakuis.redis.domain.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/log/v1/logs")
class LogRestController(val logService: LogService) {
    @PostMapping
    fun something() {
        logService.register()
    }

    @GetMapping
    fun logs(): List<Log> {
        return logService.list()
    }
}