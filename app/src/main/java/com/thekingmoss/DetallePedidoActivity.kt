package com.thekingmoss.ui.pedido

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thekingmoss.adaptador.DetallePedidoAdapter
import com.thekingmoss.databinding.ActivityDetallePedidoBinding
import com.thekingmoss.entity.Pedido

class DetallePedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallePedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallePedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pedido = intent.getParcelableExtra<Pedido>("pedido")
        pedido?.let { configurarVista(it) }
    }

    private fun configurarVista(pedido: Pedido) {

        binding.rvDetallePedido.layoutManager = LinearLayoutManager(this)
        binding.rvDetallePedido.adapter =
            DetallePedidoAdapter(pedido.detallePedidos)

        val total = pedido.detallePedidos.sumOf {
            it.producto.precioUnitario * it.cantidad
        }

        binding.tvTotalPedido.text = "Total: S/ %.2f".format(total)
    }
}
