package io.duhanmo.paymentmvcpractice.adapter.persistence.payment

import org.springframework.data.jpa.repository.JpaRepository

interface PaymentJpaRepository : JpaRepository<PaymentEntity, Long> {
    fun existsByPaymentKey(paymentKey: String): Boolean
}
