package com.thekingmoss.repository

import com.thekingmoss.services.ApiServiceProductoImagen
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProductoImagenRepository(private val service: ApiServiceProductoImagen) {
    fun listarProductoImagenes() =
        service.fetchProductoImagenes()

    fun buscarProductoImagenPorId(id: Long) =
        service.fetchProductoImagenById(id)

    fun crearProductoImagen(
        imagen: MultipartBody.Part,
        productoId: RequestBody
    ) =
        service.fetchCrearProductoImagen(imagen, productoId)

    fun actualizarProductoImagen(
        id: Long,
        imagen: MultipartBody.Part?,
        productoId: RequestBody
    ) =
        service.fetchActualizarProductoImagen(id, imagen, productoId)

    fun eliminarProductoImagen(id: Long) =
        service.fetchEliminarProductoImagen(id)
}