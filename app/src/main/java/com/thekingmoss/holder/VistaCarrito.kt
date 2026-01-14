package com.thekingmoss.holder

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thekingmoss.R
import com.thekingmoss.entity.CarritoItem

class VistaCarrito(vista: View) : RecyclerView.ViewHolder(vista) {

    var imgProducto: ImageView = vista.findViewById(R.id.imgProducto)
    var tvNombre: TextView = vista.findViewById(R.id.tvNombre)
    var tvPrecio: TextView = vista.findViewById(R.id.tvPrecio)
    var tvSubtotal: TextView = vista.findViewById(R.id.tvSubtotal)
    var tvCantidad: TextView = vista.findViewById(R.id.tvCantidad)
    var btnMas: Button = vista.findViewById(R.id.btnMas)
    var btnMenos: Button = vista.findViewById(R.id.btnMenos)
    var btnEliminar: ImageButton = vista.findViewById(R.id.btnEliminar)

    fun bind(
        item: CarritoItem,
        onMas: (CarritoItem) -> Unit,
        onMenos: (CarritoItem) -> Unit,
        onEliminar: (CarritoItem) -> Unit
    ) {
        tvNombre.text = item.nombre
        tvPrecio.text = "Precio: S/ %.2f".format(item.precio)
        tvCantidad.text = item.cantidad.toString()
        tvSubtotal.text = "Subtotal: S/ %.2f".format(item.precio * item.cantidad)

        val imageUrl = item.imagenUrl
            .replace("localhost", "192.168.100.16")

        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imgProducto)


        btnMas.setOnClickListener {
            if (item.cantidad < item.stock) onMas(item)
        }

        btnMenos.setOnClickListener {
            if (item.cantidad > 1) onMenos(item)
        }

        btnEliminar.setOnClickListener {
            onEliminar(item)
        }
    }
}

