package com.thekingmoss.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.R
import com.thekingmoss.entity.ProductoItem
import com.thekingmoss.holder.VistaProducto
import java.util.ArrayList

class ProductoAdapter(
    private var lista: ArrayList<ProductoItem>,
    private val onVer: (ProductoItem) -> Unit,
    private val onAgregar: (ProductoItem) -> Unit
) : RecyclerView.Adapter<VistaProducto>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaProducto {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return VistaProducto(item)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: VistaProducto, position: Int) {
        holder.bind(lista[position], onVer, onAgregar)
    }

    fun actualizarLista(nuevaLista: ArrayList<ProductoItem>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

}