package com.thekingmoss.adaptador

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.R
import com.thekingmoss.entity.CarritoItem
import com.thekingmoss.holder.VistaCarrito

class CarritoAdapter(
    var lista: ArrayList<CarritoItem>,
    private val onMas: (CarritoItem) -> Unit,
    private val onMenos: (CarritoItem) -> Unit,
    private val onEliminar: (CarritoItem) -> Unit
) : RecyclerView.Adapter<VistaCarrito>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaCarrito {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return VistaCarrito(item)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: VistaCarrito, position: Int) {
        val item = lista[position]
        holder.bind(item, onMas, onMenos, onEliminar)
    }

    fun actualizarLista(nuevaLista: ArrayList<CarritoItem>) {
        Log.d("CarritoAdapter", "Items recibidos: ${nuevaLista.size}")
        lista = nuevaLista
        notifyDataSetChanged()
    }
}