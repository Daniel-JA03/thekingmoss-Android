package com.thekingmoss.repository

import com.thekingmoss.dto.pedido.PedidoRequestDto
import com.thekingmoss.dto.pedido.PedidoResponseDto
import com.thekingmoss.entity.Pedido
import com.thekingmoss.mapper.PedidoMapper
import com.thekingmoss.services.ApiServicePedido
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidoRepository(
    private val apiService: ApiServicePedido
) {


    fun listarPedidosPorUsuario(
        usuarioId: Long,
        onResult: (List<Pedido>) -> Unit
    ) {
        apiService.listarPedidosPorUsuario(usuarioId)
            .enqueue(object : Callback<List<PedidoResponseDto>> {

                override fun onResponse(
                    call: Call<List<PedidoResponseDto>>,
                    response: Response<List<PedidoResponseDto>>
                ) {
                    if (response.isSuccessful) {
                        val pedidosDto = response.body() ?: emptyList()
                        val pedidos = PedidoMapper.fromDtoList(pedidosDto)
                        onResult(pedidos)
                    } else {
                        onResult(emptyList())
                    }
                }

                override fun onFailure(
                    call: Call<List<PedidoResponseDto>>,
                    t: Throwable
                ) {
                    onResult(emptyList())
                }
            })
    }

    fun obtenerPedidoPorId(
        pedidoId: Long,
        onResult: (Pedido?) -> Unit
    ) {
        apiService.obtenerPedidoPorId(pedidoId)
            .enqueue(object : Callback<PedidoResponseDto> {

                override fun onResponse(
                    call: Call<PedidoResponseDto>,
                    response: Response<PedidoResponseDto>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val pedido = PedidoMapper.fromDto(response.body()!!)
                        onResult(pedido)
                    } else {
                        onResult(null)
                    }
                }

                override fun onFailure(call: Call<PedidoResponseDto>, t: Throwable) {
                    onResult(null)
                }
            })
    }

    fun registrarPedido(
        pedidoRequest: PedidoRequestDto,
        onResult: (Pedido?) -> Unit
    ) {
        apiService.registrarPedido(pedidoRequest)
            .enqueue(object : Callback<PedidoResponseDto> {

                override fun onResponse(
                    call: Call<PedidoResponseDto>,
                    response: Response<PedidoResponseDto>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val pedido = PedidoMapper.fromDto(response.body()!!)
                        onResult(pedido)
                    } else {
                        onResult(null)
                    }
                }

                override fun onFailure(call: Call<PedidoResponseDto>, t: Throwable) {
                    onResult(null)
                }
            })
    }
}
