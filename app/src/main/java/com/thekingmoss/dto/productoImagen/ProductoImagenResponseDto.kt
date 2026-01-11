package com.thekingmoss.dto.productoImagen

data class ProductoImagenResponseDto(
    val productoImagenId: Long,
    val imagenUrl: String,
    val productoId: Long,
    val nombreProducto: String
)
