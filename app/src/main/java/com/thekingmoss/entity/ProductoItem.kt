package com.thekingmoss.entity

data class ProductoItem(
    val id: Long,
    val nombre: String,
    val precio: Double,
    val descuento: Double,
    val stock: Int,
    val descripcion: String?,
    val categoria: String
)
