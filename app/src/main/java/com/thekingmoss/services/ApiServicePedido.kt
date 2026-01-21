package com.thekingmoss.services

import com.thekingmoss.dto.pedido.PedidoRequestDto
import com.thekingmoss.dto.pedido.PedidoResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServicePedido {

    // 🔹 HISTORIAL DE PEDIDOS POR USUARIO
    @GET("api/pedidos/usuario/{usuarioId}")
    fun listarPedidosPorUsuario(
        @Path("usuarioId") usuarioId: Long
    ): Call<List<PedidoResponseDto>>

    // 🔹 DETALLE DE UN PEDIDO
    @GET("api/pedidos/{pedidoId}")
    fun obtenerPedidoPorId(
        @Path("pedidoId") pedidoId: Long
    ): Call<PedidoResponseDto>

    // 🔹 REGISTRAR / CONFIRMAR PEDIDO (checkout)
    @POST("api/pedidos")
    fun registrarPedido(
        @Body pedido: PedidoRequestDto
    ): Call<PedidoResponseDto>
}
