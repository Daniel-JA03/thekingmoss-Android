package com.thekingmoss

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.adaptador.ProductoAdapter
import com.thekingmoss.controlador.CarritoViewModel
import com.thekingmoss.controlador.CarritoViewModelFactory
import com.thekingmoss.controlador.ProductoViewModel
import com.thekingmoss.entity.SessionManager
import com.thekingmoss.repository.CarritoRepository
import com.thekingmoss.repository.ProductoRepository
import com.thekingmoss.services.ApiServiceCarrito
import com.thekingmoss.utils.ApiUtils
import com.thekingmoss.utils.RetrofitClient

class ProductoActivity : AppCompatActivity() {

    private lateinit var rvProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private lateinit var viewModel: ProductoViewModel

    private lateinit var carritoViewModel: CarritoViewModel
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        session = SessionManager(this)
        carritoViewModel = provideCarritoViewModel(this, session.getToken())


        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvProductos = findViewById(R.id.rvProductos)
        rvProductos.layoutManager = LinearLayoutManager(this)

        adapter = ProductoAdapter(
            arrayListOf(),
            onVer = { producto ->
                val intent = Intent(this, DetalleProductoActivity::class.java)
                intent.putExtra("producto", producto)
                startActivity(intent)
            },
            onAgregar = { producto ->
                carritoViewModel.agregarProducto(
                    session.getUserId(),
                    producto,
                    1 // cantidad por defecto desde la lista
                )

                Toast.makeText(
                    this,
                    "Añadido: ${producto.nombre}",
                    Toast.LENGTH_SHORT
                ).show()
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

    private fun provideCarritoViewModel(
        owner: ViewModelStoreOwner,
        token: String
    ): CarritoViewModel {

        val retrofit = RetrofitClient.getClient(
            ApiUtils.BASE_URL,
            token
        )

        val service = retrofit.create(ApiServiceCarrito::class.java)
        val repository = CarritoRepository(service)
        val factory = CarritoViewModelFactory(repository)

        return ViewModelProvider(owner, factory)
            .get(CarritoViewModel::class.java)
    }

}