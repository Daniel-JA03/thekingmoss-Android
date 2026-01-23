package com.thekingmoss.mapper

import com.thekingmoss.dto.producto.ProductoResponseDto
import com.thekingmoss.entity.ProductoItem

fun ProductoResponseDto.toProductoItem(): ProductoItem {
    return ProductoItem(
        id = idProducto,
        nombre = nombreProducto,
        precio = precioUnitario,
        descuento = descuento,
        stock = stock,
        descripcion = descripcion,
        categoria = nombreCategoria
    )
}