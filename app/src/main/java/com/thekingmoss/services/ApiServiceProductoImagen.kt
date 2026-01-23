package com.thekingmoss.services

import com.thekingmoss.dto.productoImagen.ProductoImagenResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiServiceProductoImagen {
    @GET("/api/productoImagen")
    fun fetchProductoImagenes(): Call<List<ProductoImagenResponseDto>>

    @GET("/api/productoImagen/{id}")
    fun fetchProductoImagenById(
        @Path("id") id: Long
    ): Call<ProductoImagenResponseDto>

    @Multipart
    @POST("/api/productoImagen")
    fun fetchCrearProductoImagen(
        @Part img: MultipartBody.Part,
        @Part("productoId") productoId: RequestBody
    ): Call<ProductoImagenResponseDto>

    @Multipart
    @PUT("/api/productoImagen/{id}")
    fun fetchActualizarProductoImagen(
        @Path("id") id: Long,
        @Part img: MultipartBody.Part?,
        @Part("productoId") productoId: RequestBody
    ): Call<ProductoImagenResponseDto>

    @DELETE("/api/productoImagen/{id}")
    fun fetchEliminarProductoImagen(
        @Path("id") id: Long
    ): Call<Void>
}