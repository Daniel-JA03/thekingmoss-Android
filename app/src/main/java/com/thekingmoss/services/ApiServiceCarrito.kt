package com.thekingmoss.services


import com.thekingmoss.dto.carrito.CarritoRequestDto
import com.thekingmoss.dto.carrito.CarritoResponseDto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.*
interface ApiServiceCarrito {


    @GET("/api/carrito/usuario/{usuarioId}")
    fun fetchCarritoByUsuario(
        @Path("usuarioId") usuarioId: Long
    ): Call<List<CarritoResponseDto>>

    @POST("/api/carrito/usuario/{usuarioId}/agregar")
    fun fetchAgregarProducto(
        @Path("usuarioId") usuarioId: Long,
        @Body request: CarritoRequestDto
    ): Call<CarritoResponseDto>

    @PUT("/api/carrito/usuario/{usuarioId}/actualizar")
    fun fetchActualizarCantidad(
        @Path("usuarioId") usuarioId: Long,
        @Body request: CarritoRequestDto
    ): Call<CarritoResponseDto>

    @DELETE("/api/carrito/usuario/{usuarioId}/producto/{productoId}")
    fun fetchEliminarProducto(
        @Path("usuarioId") usuarioId: Long,
        @Path("productoId") productoId: Long
    ): Call<Void>

    @DELETE("/api/carrito/usuario/{usuarioId}/vaciar")
    fun fetchVaciarCarrito(
        @Path("usuarioId") usuarioId: Long
    ): Call<Void>
}