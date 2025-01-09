package io.duhanmo.paymentmvcpractice.adapter.util

import io.duhanmo.paymentmvcpractice.core.payment.port.PaymentKeyGenerator
import org.springframework.stereotype.Component
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import java.util.UUID

@Component
class DefaultPaymentKeyGenerator : PaymentKeyGenerator {
    override fun generate(): String {
        val dateTime = now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val random = UUID.randomUUID().toString().takeLast(4)
        return dateTime + random
    }
}
