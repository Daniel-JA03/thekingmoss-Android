package com.thekingmoss.mapper

import com.thekingmoss.dto.carrito.CarritoResponseDto
import com.thekingmoss.entity.CarritoItem

fun CarritoResponseDto.toCarritoItem(): CarritoItem {
    return CarritoItem(
        idCarrito = idCarrito,
        productoId = producto.productoId,
        nombre = producto.nombreProducto,
        imagenUrl = producto.imagenUrl,
        precio = producto.precioUnitario.toDouble(),
        cantidad = cantidad,
        stock = producto.stock
    )
}