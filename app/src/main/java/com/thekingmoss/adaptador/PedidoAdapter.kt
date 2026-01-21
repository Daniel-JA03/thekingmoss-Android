package com.thekingmoss.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.R
import com.thekingmoss.entity.Pedido
import com.thekingmoss.holder.PedidoViewHolder

class PedidoAdapter(
    private var pedidos: List<Pedido>,
    private val onClick: (Pedido) -> Unit
) : RecyclerView.Adapter<PedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        holder.bind(pedidos[position], onClick)
    }

    override fun getItemCount(): Int = pedidos.size

    fun updateData(nuevaLista: List<Pedido>) {
        pedidos = nuevaLista
        notifyDataSetChanged()
    }
}
