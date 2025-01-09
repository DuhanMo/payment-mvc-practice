package io.duhanmo.paymentmvcpractice.domain.payment.model

import io.duhanmo.paymentmvcpractice.domain.payment.model.Currency.KRW
import io.duhanmo.paymentmvcpractice.domain.payment.model.PaymentStatus.APPROVED
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime.now

class PaymentTest : StringSpec({
    "결제 최초 생성 시 APPROVED 상태이다" {
        val payment = Payment(
            orderId = "orderId",
            paymentKey = "paymentKey",
            amount = 20_000,
            currency = KRW,
            createdAt = now(),
        )

        payment.status shouldBe APPROVED
    }
})