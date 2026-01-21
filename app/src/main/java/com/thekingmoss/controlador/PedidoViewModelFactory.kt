package com.thekingmoss.controlador

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thekingmoss.repository.PedidoRepository

class PedidoViewModelFactory(
    private val repository: PedidoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PedidoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PedidoViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconocido")
    }
}
