package com.thekingmoss.controlador

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thekingmoss.dto.producto.ProductoResponseDto
import com.thekingmoss.entity.ProductoItem
import com.thekingmoss.mapper.toProductoItem
import com.thekingmoss.repository.ProductoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductoViewModel(
    private val repository: ProductoRepository
) : ViewModel() {

    val productos = MutableLiveData<List<ProductoItem>>()

    fun cargarProductos() {
        repository.listarProductos()
            .enqueue(object : Callback<List<ProductoResponseDto>> {
                override fun onResponse(
                    call: Call<List<ProductoResponseDto>>,
                    response: Response<List<ProductoResponseDto>>
                ) {
                    if (response.isSuccessful) {
                        productos.value = response.body()?.map {
                            it.toProductoItem()
                        }
                    }
                }

                override fun onFailure(call: Call<List<ProductoResponseDto>>, t: Throwable) {
                    Log.e("Producto", "Error cargando productos", t)
                }
            })
    }

    fun cargarProductosPorCategoria(nombreCategoria: String) {
        repository.listarProductosPorCategoria(nombreCategoria)
            .enqueue(object : Callback<List<ProductoResponseDto>> {
                override fun onResponse(
                    call: Call<List<ProductoResponseDto>>,
                    response: Response<List<ProductoResponseDto>>
                ) {
                    if (response.isSuccessful) {
                        productos.value = response.body()?.map {
                            it.toProductoItem()
                        }
                    }
                }

                override fun onFailure(call: Call<List<ProductoResponseDto>>, t: Throwable) {
                    Log.e("Producto", "Error por categoria", t)
                }
            })
    }
}