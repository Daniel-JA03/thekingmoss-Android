package com.thekingmoss.dto.pedido

import com.thekingmoss.dto.detallePedido.DetallePedidoResponseDto

data class PedidoResponseDto(
    val pedidoId: Long,
    val fechaPedido: String,
    val tipoEntrega: String,
    val informacionPedido: String,
    val instruccionEntrega: String,
    val tipoEstadoPedido: String,
    val usuarioId: Long,
    val stripePaymentId: String?,
    val detalle: List<DetallePedidoResponseDto>
)
