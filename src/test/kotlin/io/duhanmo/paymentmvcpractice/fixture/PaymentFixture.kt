package io.duhanmo.paymentmvcpractice.fixture

import io.duhanmo.paymentmvcpractice.core.common.Money
import io.duhanmo.paymentmvcpractice.core.payment.model.Currency.KRW
import io.duhanmo.paymentmvcpractice.core.payment.model.Payment
import io.duhanmo.paymentmvcpractice.core.payment.model.PaymentStatus.APPROVED
import java.math.BigDecimal
import java.time.Instant.now

fun createPayment(
    orderId: String = "orderId",
    paymentKey: String = "paymentKey",
) = Payment(
    orderId = orderId,
    paymentKey = paymentKey,
    money = Money(BigDecimal(10_000)),
    currency = KRW,
    status = APPROVED,
    createdAt = now(),
    updatedAt = now(),
    canceledAt = null,
)
