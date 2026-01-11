package com.thekingmoss.dto.payment

data class PaymentConfirmationRequestDto(
    val pedidoId: Long,
    val stripePaymentId: String
)
