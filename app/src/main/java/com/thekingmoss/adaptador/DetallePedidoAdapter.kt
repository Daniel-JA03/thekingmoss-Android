package com.thekingmoss.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.R
import com.thekingmoss.entity.DetallePedido
import com.thekingmoss.holder.DetallePedidoViewHolder

class DetallePedidoAdapter(
    private var detalles: List<DetallePedido>
) : RecyclerView.Adapter<DetallePedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetallePedidoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detalle_pedido, parent, false)
        return DetallePedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetallePedidoViewHolder, position: Int) {
        holder.bind(detalles[position])
    }

    override fun getItemCount(): Int = detalles.size

    fun updateData(nuevaLista: List<DetallePedido>) {
        detalles = nuevaLista
        notifyDataSetChanged()
    }
}
