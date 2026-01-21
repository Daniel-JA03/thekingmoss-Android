package com.thekingmoss.mapper

import com.thekingmoss.dto.detallePedido.DetallePedidoResponseDto
import com.thekingmoss.entity.DetallePedido
import com.thekingmoss.entity.ProductoResumen

object DetallePedidoMapper {

    fun fromDto(dto: DetallePedidoResponseDto): DetallePedido {
        return DetallePedido(
            producto = ProductoResumen(
                productoId = dto.productoId,
                nombre = dto.nombreProducto,
                precioUnitario = dto.precioUnitario,
                imagenUrl = null // backend no lo envía
            ),
            cantidad = dto.cantidad
        )
    }

    fun fromDtoList(lista: List<DetallePedidoResponseDto>): List<DetallePedido> {
        return lista.map { fromDto(it) }
    }
}
