package io.duhanmo.paymentmvcpractice.mock

import io.duhanmo.paymentmvcpractice.core.payment.port.PaymentKeyGenerator

class FakePaymentKeyGenerator(private vararg val keys: String) : PaymentKeyGenerator {
    private var index = 0

    override fun generate(): String = keys.getOrElse(index++) { throw IllegalStateException("No more keys available") }
}
