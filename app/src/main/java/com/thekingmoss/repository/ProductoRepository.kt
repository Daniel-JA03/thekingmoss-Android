package com.thekingmoss.repository

import com.thekingmoss.dto.producto.ProductoRequestDto
import com.thekingmoss.services.ApiServiceProducto

class ProductoRepository(private val service: ApiServiceProducto) {
    fun listarProductos() =
        service.fetchProductos()

    fun buscarProductoPorId(id: Long) =
        service.fetchProductoById(id)

    fun listarProductosPorCategoria(nombreCategoria: String) =
        service.fetchProductosByCategoria(nombreCategoria)

    fun crearProducto(dto: ProductoRequestDto) =
        service.fetchCrearProducto(dto)

    fun actualizarProducto(id: Long, dto: ProductoRequestDto) =
        service.fetchActualizarProducto(id, dto)

    fun eliminarProducto(id: Long) =
        service.fetchEliminarProducto(id)
}