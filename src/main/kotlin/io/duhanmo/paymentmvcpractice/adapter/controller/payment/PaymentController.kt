package io.duhanmo.paymentmvcpractice.adapter.controller.payment

import io.duhanmo.paymentmvcpractice.adapter.controller.common.ApiResponse
import io.duhanmo.paymentmvcpractice.adapter.controller.payment.dto.PaymentCreateRequest
import io.duhanmo.paymentmvcpractice.adapter.controller.payment.dto.PaymentCreateResponse
import io.duhanmo.paymentmvcpractice.adapter.controller.payment.dto.PaymentCreateResponse.Companion.from
import io.duhanmo.paymentmvcpractice.core.payment.service.PaymentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    private val service: PaymentService,
) {
    @PostMapping
    fun create(
        @RequestBody request: PaymentCreateRequest,
    ): ApiResponse<PaymentCreateResponse> = ApiResponse.success(from(service.create(request.toCommand())))
}
