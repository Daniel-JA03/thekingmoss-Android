package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Pedido(
    val pedidoId: Long,
    val fechaPedido: String,
    val tipoEntrega: String,
    val tipoEstadoPedido: String,
    val detallePedidos: List<DetallePedido>
) : Parcelable { }