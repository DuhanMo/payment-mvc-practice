package io.duhanmo.paymentmvcpractice.core.common

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MoneyTest : StringSpec({
    "금액은 0보다 작게 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> { Money(-500) }
    }

    "금액과 금액을 더하면 합친 금액이 반환된다" {
        Money(500) + Money(500) shouldBe Money(1_000)
    }

    "금액과 금액을 빼면 뺀 금액이 반환된다" {
        Money(700) - Money(200) shouldBe Money(500)
    }

    "작은 금액에서 큰 금액을 뺄 수 없다" {
        shouldThrow<IllegalArgumentException> { Money(100) - Money(500) }
    }

    "금액과 수를 곱하면 곱해진 금액이 반환된다" {
        Money(100) * 2 shouldBe Money(200)
    }

    "금액에서 수를 나누면 나눠진 금액이 반환된다" {
        Money(100) / 2 shouldBe Money(50)
    }

    "금액을 0으로 나눌 수 없다" {
        shouldThrow<IllegalArgumentException> { Money(100) / 0 }
    }
})
