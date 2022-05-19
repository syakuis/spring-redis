package io.github.syakuis.redis.cache.domain

import javax.persistence.*

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-19
 */
@Entity
@Table(name = "sample")
class SampleEntity(
    @Column(nullable = false)
    var name: String
) {
    @Id
    @GeneratedValue
    var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SampleEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}