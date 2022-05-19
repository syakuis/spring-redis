package io.github.syakuis.redis.cache.service

import io.github.syakuis.redis.cache.domain.SampleEntity
import io.github.syakuis.redis.cache.domain.SampleRepository
import io.github.syakuis.redis.cache.model.SampleDto
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
@Service
@Transactional
class SampleService(val sampleRepository: SampleRepository) {

    @CacheEvict(value = ["sample"], key = "'sample.all'")
    fun save(sampleEntity: SampleEntity): SampleDto {
        val sampleEntity = sampleRepository.save(sampleEntity)
        return SampleDto(sampleEntity.id, sampleEntity.name)
    }

    @Cacheable(value = ["sample"], key = "'sample.all'")
    fun many(): List<SampleDto> {
        return sampleRepository.findAll().map { SampleDto(it.id, it.name) }
    }

    fun one(id: Long): SampleDto {
        val sampleEntity = sampleRepository.findById(id).orElseThrow()
        return SampleDto(sampleEntity.id, sampleEntity.name)
    }

}