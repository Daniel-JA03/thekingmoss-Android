package com.thekingmoss.dto.producto

data class ProductoResponseDto(
    val idProducto: Long,
    val nombreProducto: String,
    val stock: Int,
    val precioUnitario: Double,
    val descuento: Double,
    val descripcion: String?,
    val tamanio: String?,
    val peso: Double?,
    val categoriaId: Long,
    val nombreCategoria: String
)
