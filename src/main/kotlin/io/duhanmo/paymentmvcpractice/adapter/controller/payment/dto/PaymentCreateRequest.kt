package io.duhanmo.paymentmvcpractice.adapter.controller.payment.dto

import io.duhanmo.paymentmvcpractice.core.common.Money
import io.duhanmo.paymentmvcpractice.core.payment.model.Currency
import io.duhanmo.paymentmvcpractice.core.payment.service.PaymentCreateCommand

data class PaymentCreateRequest(
    val orderId: String,
    val amount: Long,
    val currency: Currency,
) {
    fun toCommand(): PaymentCreateCommand = PaymentCreateCommand(orderId, Money(amount), currency)
}
