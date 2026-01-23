package com.thekingmoss.mapper

import com.thekingmoss.dto.productoImagen.ProductoImagenResponseDto
import com.thekingmoss.entity.ProductoImagenItem

fun ProductoImagenResponseDto.toProductoImagenItem(): ProductoImagenItem {
    return ProductoImagenItem(
        id = productoImagenId,
        imagenUrl = imagenUrl,
        productoId = productoId,
        nombreProducto = nombreProducto
    )
}
