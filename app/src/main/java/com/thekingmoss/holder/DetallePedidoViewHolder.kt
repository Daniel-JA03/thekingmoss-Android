package com.thekingmoss.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.R
import com.thekingmoss.entity.DetallePedido

class DetallePedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvProducto: TextView = itemView.findViewById(R.id.tvProducto)
    private val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
    private val tvSubtotal: TextView = itemView.findViewById(R.id.tvSubtotal)

    fun bind(detalle: DetallePedido) {
        tvProducto.text = detalle.producto.nombre
        tvCantidad.text = "Cantidad: ${detalle.cantidad}"
        tvSubtotal.text = "S/ %.2f".format(
            detalle.producto.precioUnitario * detalle.cantidad
        )
    }
}
