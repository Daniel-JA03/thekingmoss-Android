package com.thekingmoss.repository

import com.thekingmoss.dto.carrito.CarritoRequestDto
import com.thekingmoss.services.ApiServiceCarrito

class CarritoRepository(private val service: ApiServiceCarrito) {
    fun obtenerCarrito(usuarioId: Long) =
        service.fetchCarritoByUsuario(usuarioId)

    fun agregarProducto(usuarioId: Long, dto: CarritoRequestDto) =
        service.fetchAgregarProducto(usuarioId, dto)

    fun actualizarCantidad(usuarioId: Long, dto: CarritoRequestDto) =
        service.fetchActualizarCantidad(usuarioId, dto)

    fun eliminarProducto(usuarioId: Long, productoId: Long) =
        service.fetchEliminarProducto(usuarioId, productoId)

    fun vaciarCarrito(usuarioId: Long) =
        service.fetchVaciarCarrito(usuarioId)
}