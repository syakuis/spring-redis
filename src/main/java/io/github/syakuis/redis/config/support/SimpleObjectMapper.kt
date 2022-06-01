package io.github.syakuis.redis.config.support;

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule

class SimpleObjectMapper {
    companion object {
        fun of(objectMapper: ObjectMapper): ObjectMapper {
            val mapper = objectMapper.copy()
            mapper.registerModule(ParameterNamesModule())
            mapper.registerModule(Jdk8Module())
            mapper.registerModule(JavaTimeModule())
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            mapper.registerModule(KotlinModule())
            return mapper;
        }
    }
}
