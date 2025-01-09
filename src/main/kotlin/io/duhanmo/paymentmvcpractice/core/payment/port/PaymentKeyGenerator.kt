package io.duhanmo.paymentmvcpractice.core.payment.port

interface PaymentKeyGenerator {
    fun generate(): String
}
