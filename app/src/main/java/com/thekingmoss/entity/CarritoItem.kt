package com.thekingmoss.entity

data class CarritoItem(
    val idCarrito: Long,
    val productoId: Long,
    val nombre: String,
    val imagenUrl: String,
    val precio: Double,
    val cantidad: Int,
    val stock: Int
) {
    val subtotal: Double
        get() = precio * cantidad
}
