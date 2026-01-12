package com.thekingmoss.entity

class Carrito(
    val idCarrito: Long,
    val productoId: Long,
    val nombre: String,
    val imagenUrl: String,
    val precio: Double,
    val cantidad: Int,
    val stock: Int
) { }