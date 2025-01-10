package io.duhanmo.paymentmvcpractice.core.common

import java.math.BigDecimal

@JvmInline
value class Money(val amount: BigDecimal) {
    init {
        require(amount >= BigDecimal.ZERO) { "금액은 0보다 작을 수 없습니다" }
    }

    constructor(amount: Long) : this(
        amount = BigDecimal(amount),
    )

    operator fun plus(other: Money): Money = Money(this.amount + other.amount)

    operator fun minus(other: Money): Money {
        val result = this.amount - other.amount
        require(result >= BigDecimal.ZERO) { "빼기 결과가 0보다 작을 수 없습니다" }
        return Money(result)
    }

    operator fun times(multiplier: Long): Money = Money(this.amount * BigDecimal(multiplier))

    operator fun div(divisor: Long): Money {
        require(BigDecimal(divisor) != BigDecimal.ZERO) { "0으로 나눌 수 없습니다" }
        return Money(this.amount / BigDecimal(divisor))
    }

    override fun toString(): String = "$amount"
}
