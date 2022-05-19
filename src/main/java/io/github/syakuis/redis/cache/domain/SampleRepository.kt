package io.github.syakuis.redis.cache.domain

import org.springframework.data.repository.CrudRepository

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
interface SampleRepository: CrudRepository<SampleEntity, Long>