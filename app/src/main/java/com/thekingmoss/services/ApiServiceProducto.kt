package com.thekingmoss.services

import com.thekingmoss.dto.producto.ProductoRequestDto
import com.thekingmoss.dto.producto.ProductoResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.*

interface ApiServiceProducto {
    @GET("/api/producto")
    fun fetchProductos(): Call<List<ProductoResponseDto>>

    @GET("/api/producto/{id}")
    fun fetchProductoById(
        @Path("id") id: Long
    ): Call<ProductoResponseDto>

    @GET("/api/producto/categoria/{nombreCategoria}")
    fun fetchProductosByCategoria(
        @Path("nombreCategoria") nombreCategoria: String
    ): Call<List<ProductoResponseDto>>

    @POST("/api/producto")
    fun fetchCrearProducto(
        @Body request: ProductoRequestDto
    ): Call<ProductoResponseDto>

    @PUT("/api/producto/{id}")
    fun fetchActualizarProducto(
        @Path("id") id: Long,
        @Body request: ProductoRequestDto
    ): Call<ProductoResponseDto>

    @DELETE("/api/producto/{id}")
    fun fetchEliminarProducto(
        @Path("id") id: Long
    ): Call<Void>
}