package io.duhanmo.paymentmvcpractice.adapter.controller.payment.dto

import io.duhanmo.paymentmvcpractice.core.payment.model.Currency
import io.duhanmo.paymentmvcpractice.core.payment.model.Payment
import io.duhanmo.paymentmvcpractice.core.payment.model.PaymentStatus

data class PaymentCreateResponse(
    val orderId: String,
    val paymentKey: String,
    val amount: Long,
    val currency: Currency,
    val status: PaymentStatus,
) {
    companion object {
        fun from(payment: Payment): PaymentCreateResponse =
            PaymentCreateResponse(
                orderId = payment.orderId,
                paymentKey = payment.paymentKey,
                amount = payment.money.amount.toLong(),
                currency = payment.currency,
                status = payment.status,
            )
    }
}
