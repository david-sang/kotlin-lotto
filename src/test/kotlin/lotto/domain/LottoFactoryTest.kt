package lotto.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec

class LottoFactoryTest : StringSpec({
    "랜덤으로 로또를 생성한다." {
        shouldNotThrowAny {
            LottoFactory.generateRandom()
        }
    }
})