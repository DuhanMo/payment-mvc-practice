package io.duhanmo.paymentmvcpractice.core.payment.model

import io.duhanmo.paymentmvcpractice.core.common.Money
import java.time.Instant

class Payment(
    val id: Long = 0L,
    val orderId: String,
    val paymentKey: String,
    val money: Money,
    val currency: Currency,
    val status: PaymentStatus,
    val createdAt: Instant,
    val updatedAt: Instant,
    val canceledAt: Instant?,
)
