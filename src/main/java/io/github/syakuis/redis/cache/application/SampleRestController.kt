package io.github.syakuis.redis.cache.application

import io.github.syakuis.redis.cache.domain.SampleEntity
import io.github.syakuis.redis.cache.model.SampleDto
import io.github.syakuis.redis.cache.service.SampleService
import org.springframework.web.bind.annotation.*

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
@RestController
@RequestMapping("/sample/v1/samples")
class SampleRestController(val sampleService: SampleService) {

    @PostMapping
    fun register(@RequestBody name: String) {
        sampleService.save(SampleEntity(name))
    }

    @GetMapping
    fun list(): List<SampleDto> {
        return sampleService.many()
    }
}