package com.thekingmoss.controlador

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thekingmoss.dto.carrito.CarritoRequestDto
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
                    Log.d("Carrito", "Response code: ${response.code()}")
                    Log.d("Carrito", "Body: ${response.body()}")

                    if (response.isSuccessful) {
                        carrito.value = response.body()?.map {
                            it.toCarritoItem()
                        }
                    }
                }


                override fun onFailure(call: Call<List<CarritoResponseDto>>, t: Throwable) {
                    Log.e("Carrito", "Error cargando carrito", t)
                }
            })
    }

    fun aumentarCantidad(usuarioId: Long, item: CarritoItem) {
        val request = CarritoRequestDto(
            productoId = item.productoId,
            cantidad = item.cantidad + 1
        )

        repository.actualizarCantidad(usuarioId, request)
            .enqueue(simpleCallback(usuarioId))
    }

    fun disminuirCantidad(usuarioId: Long, item: CarritoItem) {
        if (item.cantidad <= 1) return

        val request = CarritoRequestDto(
            productoId = item.productoId,
            cantidad = item.cantidad - 1
        )

        repository.actualizarCantidad(usuarioId, request)
            .enqueue(simpleCallback(usuarioId))
    }

    fun eliminarProducto(usuarioId: Long, productoId: Long) {
        repository.eliminarProducto(usuarioId, productoId)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        cargarCarrito(usuarioId)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("Carrito", "Error eliminar producto", t)
                }
            })
    }

    // Callback reutilizable
    private fun simpleCallback(usuarioId: Long) =
        object : Callback<CarritoResponseDto> {
            override fun onResponse(
                call: Call<CarritoResponseDto>,
                response: Response<CarritoResponseDto>
            ) {
                if (response.isSuccessful) {
                    cargarCarrito(usuarioId)
                }
            }

            override fun onFailure(call: Call<CarritoResponseDto>, t: Throwable) {
                Log.e("Carrito", "Error actualizar cantidad", t)
            }
        }
}

