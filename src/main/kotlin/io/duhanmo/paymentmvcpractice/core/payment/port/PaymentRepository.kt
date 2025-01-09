package io.duhanmo.paymentmvcpractice.core.payment.port

import io.duhanmo.paymentmvcpractice.core.payment.model.Payment

interface PaymentRepository {
    fun save(payment: Payment): Payment

    fun existsByPaymentKey(paymentKey: String): Boolean
}
