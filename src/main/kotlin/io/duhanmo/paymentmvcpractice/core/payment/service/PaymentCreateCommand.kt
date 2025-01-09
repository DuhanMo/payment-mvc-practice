package io.duhanmo.paymentmvcpractice.core.payment.service

import io.duhanmo.paymentmvcpractice.core.common.Money
import io.duhanmo.paymentmvcpractice.core.payment.model.Currency

data class PaymentCreateCommand(
    val orderId: String,
    val money: Money,
    val currency: Currency,
)
