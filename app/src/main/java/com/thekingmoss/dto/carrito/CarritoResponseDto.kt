package com.thekingmoss.dto.carrito

data class CarritoResponseDto(
    val idCarrito: Long,
    val usuarioId: Long,
    val producto: ProductoCarritoDto,
    val cantidad: Int
)
