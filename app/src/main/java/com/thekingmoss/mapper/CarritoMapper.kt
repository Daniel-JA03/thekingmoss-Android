package com.thekingmoss.mapper

import com.thekingmoss.dto.carrito.CarritoResponseDto
import com.thekingmoss.entity.Carrito

class CarritoMapper {

    fun CarritoResponseDto.toCarrito(): Carrito {
        return Carrito(
            idCarrito = idCarrito,
            productoId = producto.productoId,
            nombre = producto.nombreProducto,
            imagenUrl = producto.imagenUrl,
            precio = producto.precioUnitario.toDouble(),
            cantidad = cantidad,
            stock = producto.stock
        )
    }
}