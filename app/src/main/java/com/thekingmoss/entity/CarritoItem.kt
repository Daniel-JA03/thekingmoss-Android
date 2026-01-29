package com.thekingmoss.entity

import java.io.Serializable

data class CarritoItem(
    val idCarrito: Long,
    val productoId: Long,
    val nombre: String,
    val imagenUrl: String,
    val precio: Double,
    val cantidad: Int,
    val stock: Int
) : Serializable {
    val subtotal: Double
        get() = precio * cantidad
}
