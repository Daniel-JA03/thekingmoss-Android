package com.thekingmoss.ui.pedido

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thekingmoss.LoginActivity
import com.thekingmoss.adaptador.PedidoAdapter
import com.thekingmoss.controlador.PedidoViewModel
import com.thekingmoss.controlador.PedidoViewModelFactory
import com.thekingmoss.databinding.ActivityHistorialPedidosBinding
import com.thekingmoss.entity.SessionManager
import com.thekingmoss.repository.PedidoRepository
import com.thekingmoss.utils.ApiUtils

class HistorialPedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistorialPedidosBinding
    private lateinit var viewModel: PedidoViewModel
    private lateinit var adapter: PedidoAdapter

    // private val usuarioId = 3L // pruebas
    private lateinit var session: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistorialPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        if (!session.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        configurarRecycler()
        configurarViewModel()
    }

    private fun configurarRecycler() {
        adapter = PedidoAdapter(emptyList()) { pedido ->
            val intent = Intent(this, DetallePedidoActivity::class.java)
            intent.putExtra("pedido", pedido)
            startActivity(intent)
        }

        binding.rvPedidos.layoutManager = LinearLayoutManager(this)
        binding.rvPedidos.adapter = adapter
    }

    private fun configurarViewModel() {
        //val token = intent.getStringExtra("token") ?: ""
        val token = session.getToken()
        val usuarioId = session.getUserId()

        val pedidoService = ApiUtils.pedidoService(token)
        val repository = PedidoRepository(pedidoService)

        viewModel = ViewModelProvider(
            this,
            PedidoViewModelFactory(repository)
        )[PedidoViewModel::class.java]

        viewModel.pedidos.observe(this) { lista ->
            adapter.updateData(lista)
        }

        viewModel.cargarPedidos(usuarioId)
    }

    // para hacer pruebas
    /*private fun getToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidGVzdHVzZXIiLCJpYXQiOjE3NjkwNDUxNDMsImV4cCI6MTc2OTA4MTE0M30.W_XKryPKsAHjy7T6LPDkHW-DeoOXwBZ3EfE338Ur4yw"
    }*/
}
