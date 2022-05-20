package io.github.syakuis.redis

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.atomic.AtomicInteger
import javax.annotation.Resource

/**
 * @author Seok Kyun. Choi.
 * @since 2022-05-20
 */
@SpringBootTest
internal class RedisTemplateTest : BehaviorSpec() {
//    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var testRedisTemplate: RedisTemplate<String, String>

    init {
        val key = "test:"

        Given("RedisTemplate 자료 구조 테스트") {
            When("관련 키를 모두 제거한다.") {
                testRedisTemplate.keys(key + "*").map { testRedisTemplate.delete(it) }

                Then("제거되었다") {
                    testRedisTemplate.keys(key + "*").size shouldBe 0
                }
            }

            When("관련된 모든 키를 검색한다.") {
                val id = "set:"

                testRedisTemplate.opsForSet().add("$key$id", "a", "b");

                Then("1개의 키가 존재한다.") {
                    testRedisTemplate.keys("test:*").size shouldBe 1
                }
            }

            When("opsForList 구조의 아이템을 수정한다.") {
                val id = "list:"

                testRedisTemplate.opsForList().rightPush("$key$id", "a")
                testRedisTemplate.opsForList().rightPush("$key$id", "b")
                testRedisTemplate.opsForList().rightPush("$key$id", "c")

                Then("아이템을 수정한다.") {
                    testRedisTemplate.opsForList().set("$key$id", 1, "cc")

                    testRedisTemplate.opsForList().index("$key$id", 1) shouldBe "cc"
                }

                Then("아이템 인덱스를 찾는 다.") {
                    testRedisTemplate.opsForList().indexOf("$key$id", "d") shouldBe null
                    testRedisTemplate.opsForList().indexOf("$key$id", "d") shouldNotBe  -1
                    testRedisTemplate.opsForList().indexOf("$key$id", "c") shouldBe 2
                }
            }

            When("auto increment") {
                Then("1 증가했다.") {
                    testRedisTemplate.opsForValue().increment(key + "auto-increment") shouldBe 1
                }
            }

        }

    }
}