package com.thekingmoss.dto.carrito

data class ProductoCarritoDto(
    val productoId: Long,
    val nombreProducto: String,
    val imagenUrl: String,
    val precioUnitario: Double,
    val stock: Int
)
