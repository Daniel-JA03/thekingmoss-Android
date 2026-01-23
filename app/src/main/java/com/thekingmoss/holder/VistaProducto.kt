package com.thekingmoss.holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thekingmoss.R
import com.thekingmoss.entity.ProductoItem

class VistaProducto(vista: View) : RecyclerView.ViewHolder(vista) {
    private val imgProducto: ImageView = vista.findViewById(R.id.imgProducto)
    private val tvNombre: TextView = vista.findViewById(R.id.tvNombre)
    private val tvDescripcion: TextView = vista.findViewById(R.id.tvDescripcion)
    private val tvPrecio: TextView = vista.findViewById(R.id.tvPrecio)
    private val btnVer: Button = vista.findViewById(R.id.btnVerProducto)
    private val btnAgregar: Button = vista.findViewById(R.id.btnAgregar)

    fun bind(
        item: ProductoItem,
        onVer: (ProductoItem) -> Unit,
        onAgregar: (ProductoItem) -> Unit
    ) {
        tvNombre.text = item.nombre
        tvDescripcion.text = item.descripcion ?: "Sin descripción"
        tvPrecio.text = "S/ %.2f".format(item.precio)

        val imageUrl = item.imagenUrl
            ?.replace("localhost", "192.168.100.16")

        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imgProducto)

        btnVer.setOnClickListener {
            onVer(item)
        }

        btnAgregar.setOnClickListener {
            onAgregar(item)
        }
    }
}