package com.thekingmoss

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.thekingmoss.entity.ProductoItem

class DetalleProductoActivity : AppCompatActivity() {

    private var cantidad = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val producto = intent.getSerializableExtra("producto") as? ProductoItem
            ?: return

        val img = findViewById<ImageView>(R.id.imgProducto)
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvPrecio = findViewById<TextView>(R.id.tvPrecio)
        val tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        val tvCantidad = findViewById<TextView>(R.id.tvCantidad)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        val btnMas = findViewById<ImageButton>(R.id.btnMas)
        val btnMenos = findViewById<ImageButton>(R.id.btnMenos)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarCarrito)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        tvNombre.text = producto.nombre
        tvPrecio.text = "Precio: S/ %.2f".format(producto.precio)
        tvDescripcion.text = producto.descripcion ?: "Sin descripción"

        fun actualizarTotal() {
            tvCantidad.text = cantidad.toString()
            tvTotal.text = "Total: S/ %.2f".format(producto.precio * cantidad)
        }

        actualizarTotal()

        btnMas.setOnClickListener {
            cantidad++
            actualizarTotal()
        }

        btnMenos.setOnClickListener {
            if (cantidad > 1) {
                cantidad--
                actualizarTotal()
            }
        }

        Glide.with(this)
            .load(producto.imagenUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(img)
        Log.d("IMG_URL", producto.imagenUrl ?: "SIN IMAGEN")


        btnAgregar.setOnClickListener {
            Toast.makeText(
                this,
                "${producto.nombre} añadido al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnVolver.setOnClickListener {
            finish()
        }

    }
}