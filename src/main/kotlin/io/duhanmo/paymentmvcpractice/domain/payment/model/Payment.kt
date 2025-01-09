package io.duhanmo.paymentmvcpractice.domain.payment.model

import io.duhanmo.paymentmvcpractice.domain.payment.model.PaymentStatus.APPROVED
import java.time.LocalDateTime

class Payment(
    val orderId: String,
    val paymentKey: String,
    val amount: Long,
    val currency: Currency,
    val createdAt: LocalDateTime,
    val status: PaymentStatus = APPROVED,
    val canceledAt: LocalDateTime? = null,
) {
    val id: Long = 0L
}
