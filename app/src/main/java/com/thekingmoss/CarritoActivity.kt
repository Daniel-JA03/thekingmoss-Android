package com.thekingmoss

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thekingmoss.adaptador.CarritoAdapter
import com.thekingmoss.controlador.CarritoViewModel
import com.thekingmoss.controlador.CarritoViewModelFactory
import com.thekingmoss.entity.CarritoItem
import com.thekingmoss.repository.CarritoRepository
import com.thekingmoss.services.ApiServiceCarrito
import com.thekingmoss.utils.ApiUtils
import com.thekingmoss.utils.RetrofitClient
import kotlin.collections.arrayListOf

class CarritoActivity : AppCompatActivity() {
    private lateinit var rvCarrito: RecyclerView
    private lateinit var tvTotal: TextView
    private lateinit var btnComprar: Button

    private lateinit var adapter: CarritoAdapter
    private lateinit var viewModel: CarritoViewModel

    private lateinit var tvCarritoVacio: TextView

    private lateinit var layoutTotal: View


    private val usuarioId = 3L // luego lo sacas del login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrito)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Carrito"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvCarrito = findViewById(R.id.rvCarrito)
        tvTotal = findViewById(R.id.tvTotal)
        btnComprar = findViewById(R.id.btnComprar)
        tvCarritoVacio = findViewById(R.id.tvCarritoVacio)
        layoutTotal = findViewById(R.id.layoutTotal)

        adapter = CarritoAdapter(
            arrayListOf(),
            onMas = { item -> viewModel.aumentarCantidad(usuarioId, item) },
            onMenos = { item -> viewModel.disminuirCantidad(usuarioId, item) },
            onEliminar = { item -> viewModel.eliminarProducto(usuarioId, item.productoId) }
        )

        rvCarrito.layoutManager = LinearLayoutManager(this)
        rvCarrito.adapter = adapter

        btnComprar.setOnClickListener {
            val total = viewModel.carrito.value
                ?.sumOf { it.precio * it.cantidad } ?: 0.0

            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("total", total)
            startActivity(intent)
        }


        setupViewModel()
        cargarCarrito()
    }

    private fun setupViewModel() {
        /*viewModel = ViewModelProvider(this)[CarritoViewModel::class.java]

        viewModel.carrito.observe(this) { lista ->
            adapter.actualizarLista(ArrayList(lista))
            calcularTotal(lista)
        }*/

        val retrofit = RetrofitClient.getClient(
            ApiUtils.BASE_URL,
            getToken()
        )

        val service = retrofit.create(ApiServiceCarrito::class.java)
        val repository = CarritoRepository(service)
        val factory = CarritoViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)
            .get(CarritoViewModel::class.java)

        // 🔥 OBSERVADOR (ESTO FALTABA)
        /*viewModel.carrito.observe(this) { lista ->
            adapter.actualizarLista(ArrayList(lista))
            calcularTotal(lista)
        }*/

        viewModel.carrito.observe(this) { lista ->

            adapter.actualizarLista(ArrayList(lista))
            calcularTotal(lista)

            if (lista.isEmpty()) {
                tvCarritoVacio.visibility = View.VISIBLE
                rvCarrito.visibility = View.GONE
                layoutTotal.visibility = View.GONE
            } else {
                tvCarritoVacio.visibility = View.GONE
                rvCarrito.visibility = View.VISIBLE
                layoutTotal.visibility = View.VISIBLE
            }
        }


    }

    private fun cargarCarrito() {
        viewModel.cargarCarrito(usuarioId)
    }

    private fun calcularTotal(lista: List<CarritoItem>) {
        if (lista.isEmpty()) {
            tvTotal.text = "Total: S/ 0.00"
            return
        }

        val total = lista.sumOf { it.precio * it.cantidad }
        tvTotal.text = "Total: S/ %.2f".format(total)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getToken(): String {
        /*val prefs = getSharedPreferences("auth", MODE_PRIVATE)
        return prefs.getString("token", "") ?: ""*/

        // por mientras para hacer pruebas poner token manualmente hasta que este culminado el login
        return "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidGVzdHVzZXIiLCJpYXQiOjE3Njg2MTM2NDgsImV4cCI6MTc2ODY0OTY0OH0.ZAdKBrbS6Dj0KOLNOSms2fYlha6_bGQJyUZSFQktrAs"
    }

}