package io.duhanmo.paymentmvcpractice.core.payment.model

import io.duhanmo.paymentmvcpractice.core.common.Money
import java.time.LocalDateTime

class Payment(
    val id: Long = 0L,
    val orderId: String,
    val paymentKey: String,
    val money: Money,
    val currency: Currency,
    val status: PaymentStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val canceledAt: LocalDateTime?,
)
