package io.duhanmo.paymentmvcpractice.adapter.persistence.payment

import io.duhanmo.paymentmvcpractice.core.common.Money
import io.duhanmo.paymentmvcpractice.core.payment.model.Currency
import io.duhanmo.paymentmvcpractice.core.payment.model.Payment
import io.duhanmo.paymentmvcpractice.core.payment.model.PaymentStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant

@Table(name = "payment")
@Entity
class PaymentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "order_id")
    val orderId: String,
    @Column(name = "payment_key")
    val paymentKey: String,
    @Column(name = "amount")
    val amount: Long,
    @Column(name = "currency")
    @Enumerated(STRING)
    val currency: Currency,
    @Column(name = "payment_status")
    @Enumerated(STRING)
    val status: PaymentStatus,
    @Column(name = "created_at")
    val createdAt: Instant,
    @Column(name = "updated_at")
    val updatedAt: Instant,
    @Column(name = "canceled_at")
    val canceledAt: Instant?,
) {
    constructor(model: Payment) : this(
        id = model.id,
        orderId = model.orderId,
        paymentKey = model.paymentKey,
        amount = model.money.amount.toLong(),
        currency = model.currency,
        status = model.status,
        createdAt = model.createdAt,
        updatedAt = model.updatedAt,
        canceledAt = model.canceledAt,
    )

    fun toModel(): Payment =
        Payment(
            id = id,
            orderId = orderId,
            paymentKey = paymentKey,
            money = Money(BigDecimal(amount)),
            currency = currency,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt,
            canceledAt = canceledAt,
        )
}
