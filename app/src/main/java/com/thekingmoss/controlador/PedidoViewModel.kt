package com.thekingmoss.controlador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thekingmoss.entity.Pedido
import com.thekingmoss.repository.PedidoRepository

class PedidoViewModel(
    private val repository: PedidoRepository
) : ViewModel() {

    // 🔹 Historial de pedidos
    private val _pedidos = MutableLiveData<List<Pedido>>()
    val pedidos: LiveData<List<Pedido>> = _pedidos

    // 🔹 Pedido seleccionado (detalle)
    private val _pedidoSeleccionado = MutableLiveData<Pedido?>()
    val pedidoSeleccionado: LiveData<Pedido?> = _pedidoSeleccionado

    // 🔹 Cargar pedidos por usuario
    fun cargarPedidos(usuarioId: Long) {
        repository.listarPedidosPorUsuario(usuarioId) { lista ->
            _pedidos.postValue(lista)
        }
    }

    // 🔹 Obtener pedido por ID (detalle)
    fun obtenerPedido(pedidoId: Long) {
        repository.obtenerPedidoPorId(pedidoId) { pedido ->
            _pedidoSeleccionado.postValue(pedido)
        }
    }
}
