package io.duhanmo.paymentmvcpractice.core.payment.service

import io.duhanmo.paymentmvcpractice.core.payment.model.Payment
import io.duhanmo.paymentmvcpractice.core.payment.model.PaymentStatus.APPROVED
import io.duhanmo.paymentmvcpractice.core.payment.port.PaymentKeyGenerator
import io.duhanmo.paymentmvcpractice.core.payment.port.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant.now

@Service
class PaymentService(
    private val paymentKeyGenerator: PaymentKeyGenerator,
    private val paymentRepository: PaymentRepository,
) {
    @Transactional
    fun create(command: PaymentCreateCommand): Payment {
        val now = now()
        return paymentRepository.save(
            Payment(
                orderId = command.orderId,
                paymentKey = createUniquePaymentKey(),
                money = command.money,
                currency = command.currency,
                status = APPROVED,
                createdAt = now,
                updatedAt = now,
                canceledAt = null,
            ),
        )
    }

    private fun createUniquePaymentKey(maxRetries: Int = 10): String {
        var paymentKey: String
        var retries = 0
        do {
            if (retries++ >= maxRetries) {
                throw IllegalStateException("Failed to generate unique payment key")
            }
            paymentKey = paymentKeyGenerator.generate()
        } while (paymentRepository.existsByPaymentKey(paymentKey))
        return paymentKey
    }
}
