package com.thekingmoss.mapper

import com.thekingmoss.dto.producto.ProductoResponseDto
import com.thekingmoss.entity.ProductoItem

fun ProductoResponseDto.toProductoItem(imagenUrl: String? = null): ProductoItem {
    return ProductoItem(
        productoId = idProducto,
        nombre = nombreProducto,
        descripcion = descripcion,
        precio = precioUnitario,
        imagenUrl = "http://localhost:8080/imagesProducts/$imagenUrl"
    )
}
