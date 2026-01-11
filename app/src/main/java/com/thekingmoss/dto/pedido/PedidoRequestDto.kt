package com.thekingmoss.dto.pedido

import com.thekingmoss.dto.detallePedido.DetallePedidoRequestDto

data class PedidoRequestDto(
    val fechaPedido: String,
    val tipoEntrega: String,
    val informacionPedido: String,
    val instruccionEntrega: String,
    val tipoEstadoPedido: String,
    val usuarioId: Long,
    val detalles: List<DetallePedidoRequestDto>
)
