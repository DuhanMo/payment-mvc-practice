package io.duhanmo.paymentmvcpractice.core.payment.service

import io.duhanmo.paymentmvcpractice.core.common.Money
import io.duhanmo.paymentmvcpractice.core.payment.model.Currency.KRW
import io.duhanmo.paymentmvcpractice.core.payment.model.PaymentStatus.APPROVED
import io.duhanmo.paymentmvcpractice.fixture.createPayment
import io.duhanmo.paymentmvcpractice.mock.FakePaymentKeyGenerator
import io.duhanmo.paymentmvcpractice.mock.FakePaymentRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class PaymentServiceTest : BehaviorSpec({
    val paymentKeyGenerator = FakePaymentKeyGenerator("exist-key", "unique-key")
    val paymentRepository = FakePaymentRepository()
    val service = PaymentService(paymentKeyGenerator, paymentRepository)

    Given("중복되는 결제키가 없는 경우") {
        paymentRepository.save(createPayment(paymentKey = "not-duplicate-payment-key"))
        val command =
            PaymentCreateCommand(
                orderId = UUID.randomUUID().toString(),
                money = Money(10_000),
                currency = KRW,
            )

        When("결제를 생성하면") {
            val actual = service.create(command)

            Then("승인 상태이다") {
                actual.status shouldBe APPROVED
            }
        }
    }

    Given("중복되는 결제키가 존재하는 경우") {
        paymentRepository.save(createPayment(paymentKey = "exist-key"))
        val command =
            PaymentCreateCommand(
                orderId = UUID.randomUUID().toString(),
                money = Money(10_000),
                currency = KRW,
            )

        When("결제를 생성하면") {
            val actual = service.create(command)

            Then("중복키를 피해서 결제 생성한다") {
                actual.paymentKey shouldBe "unique-key"
            }
        }
    }
})
