package com.thekingmoss.mapper

import com.thekingmoss.dto.producto.ProductoResponseDto
import com.thekingmoss.entity.ProductoItem


private const val BASE_URL = "http://192.168.100.16:8080"

fun ProductoResponseDto.toProductoItem(imagenUrl: String? = null): ProductoItem {
    return ProductoItem(
        productoId = idProducto,
        nombre = nombreProducto,
        descripcion = descripcion,
        precio = precioUnitario,
        imagenUrl  = imagenUrl?.let {
            "$BASE_URL/imagesProducts/$it"
        }
    )
}
