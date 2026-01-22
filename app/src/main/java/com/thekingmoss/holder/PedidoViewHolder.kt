package com.thekingmoss.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.R
import com.thekingmoss.entity.Pedido

class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvPedidoId: TextView = itemView.findViewById(R.id.tvPedidoId)
    private val tvFecha: TextView = itemView.findViewById(R.id.tvFechaPedido)
    private val tvEstado: TextView = itemView.findViewById(R.id.tvEstadoPedido)
    private val tvTotal: TextView = itemView.findViewById(R.id.tvTotal)

    fun bind(pedido: Pedido, onClick: (Pedido) -> Unit) {

        tvPedidoId.text = "Pedido #${pedido.pedidoId}"
        tvFecha.text = pedido.fechaPedido
        tvEstado.text = pedido.tipoEstadoPedido
        tvTotal.text = "Total: S/ %.2f".format(calcularTotal(pedido))

        itemView.setOnClickListener {
            onClick(pedido)
        }
    }

    private fun calcularTotal(pedido: Pedido): Double {
        return pedido.detallePedidos.sumOf {
            it.producto.precioUnitario * it.cantidad
        }
    }
}
