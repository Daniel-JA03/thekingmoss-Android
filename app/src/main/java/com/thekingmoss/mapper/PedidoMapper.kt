package com.thekingmoss.mapper

import com.thekingmoss.dto.pedido.PedidoResponseDto
import com.thekingmoss.entity.Pedido

object PedidoMapper {

    fun fromDto(dto: PedidoResponseDto): Pedido {
        return Pedido(
            pedidoId = dto.pedidoId,
            fechaPedido = dto.fechaPedido,
            tipoEntrega = dto.tipoEntrega,
            tipoEstadoPedido = dto.tipoEstadoPedido,
            detallePedidos = DetallePedidoMapper.fromDtoList(dto.detalle)
        )
    }

    fun fromDtoList(lista: List<PedidoResponseDto>): List<Pedido> {
        return lista.map { fromDto(it) }
    }
}
