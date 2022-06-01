package io.github.syakuis.redis.pubsub.adaptor.`in`

import io.github.syakuis.redis.pubsub.application.NotifyService
import io.github.syakuis.redis.pubsub.model.NotifyCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Seok Kyun. Choi.
 * @since 2022-06-01
 */
@RestController
@RequestMapping("/api/v1/notifies")
class NotifyRestController(private val notifyService: NotifyService) {

    @PostMapping
    fun register(@RequestBody notify: NotifyCommand) {
        notifyService.register(notify)
    }
}