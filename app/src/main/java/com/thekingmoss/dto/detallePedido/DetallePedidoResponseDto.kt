package com.thekingmoss.dto.detallePedido

data class DetallePedidoResponseDto(
    val productoId: Long,
    val nombreProducto: String,
    val cantidad: Int,
    val precioUnitario: Double,
    val descuento: Double
) {
    fun lineaTotal(): Double {
        return precioUnitario * cantidad
    }
}
