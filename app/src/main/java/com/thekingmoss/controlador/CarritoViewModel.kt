package com.thekingmoss.controlador

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thekingmoss.dto.carrito.CarritoResponseDto
import com.thekingmoss.entity.CarritoItem
import com.thekingmoss.mapper.toCarritoItem
import com.thekingmoss.repository.CarritoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarritoViewModel(
    private val repository: CarritoRepository
) : ViewModel() {

    val carrito = MutableLiveData<List<CarritoItem>>()

    fun cargarCarrito(usuarioId: Long) {
        repository.obtenerCarrito(usuarioId)
            .enqueue(object : Callback<List<CarritoResponseDto>> {

                override fun onResponse(
                    call: Call<List<CarritoResponseDto>>,
                    response: Response<List<CarritoResponseDto>>
                ) {
                    if (response.isSuccessful) {
                        carrito.value =
                            response.body()?.map { it.toCarritoItem() }
                    }
                }

                override fun onFailure(
                    call: Call<List<CarritoResponseDto>>,
                    t: Throwable
                ) {}
            })
    }
}
