package com.thekingmoss

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.adaptador.ProductoAdapter
import com.thekingmoss.controlador.ProductoViewModel
import com.thekingmoss.repository.ProductoRepository
import com.thekingmoss.utils.ApiUtils

class ProductoActivity : AppCompatActivity() {

    private lateinit var rvProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private lateinit var viewModel: ProductoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvProductos = findViewById(R.id.rvProductos)
        rvProductos.layoutManager = GridLayoutManager(this, 2)

        adapter = ProductoAdapter(
            arrayListOf(),
            onVer = { producto ->
                Toast.makeText(this, producto.nombre, Toast.LENGTH_SHORT).show()
                // 👉 aquí luego abres DetalleProductoActivity
            },
            onAgregar = { producto ->
                Toast.makeText(this, "Añadido: ${producto.nombre}", Toast.LENGTH_SHORT).show()
                // 👉 aquí llamas al CarritoViewModel
            }
        )

        rvProductos.adapter = adapter

        val repository = ProductoRepository(
            ApiUtils.productoService(),
            ApiUtils.productoImagenService()
        )

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProductoViewModel(repository) as T
                }
            }
        )[ProductoViewModel::class.java]

        viewModel.productos.observe(this) {
            adapter.actualizarLista(ArrayList(it))
        }

        viewModel.cargarProductos()
    }
}