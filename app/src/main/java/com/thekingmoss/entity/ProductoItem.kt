package com.thekingmoss.entity

data class ProductoItem(
    val productoId: Long,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val imagenUrl: String?
)
