package io.duhanmo.paymentmvcpractice.adapter.persistence.payment

import io.duhanmo.paymentmvcpractice.core.payment.model.Payment
import io.duhanmo.paymentmvcpractice.core.payment.port.PaymentRepository
import org.springframework.stereotype.Repository

@Repository
class PaymentRepositoryImpl(
    private val paymentJpaRepository: PaymentJpaRepository,
) : PaymentRepository {
    override fun save(payment: Payment): Payment = paymentJpaRepository.save(PaymentEntity(payment)).toModel()

    override fun existsByPaymentKey(paymentKey: String): Boolean = paymentJpaRepository.existsByPaymentKey(paymentKey)
}
