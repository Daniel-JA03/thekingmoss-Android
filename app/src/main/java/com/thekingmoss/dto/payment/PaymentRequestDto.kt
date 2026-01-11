package com.thekingmoss.dto.payment

data class PaymentRequestDto(
    val pedidoId: Long,
    val email: String,
    val amount: Long,
    val currency: String
)
