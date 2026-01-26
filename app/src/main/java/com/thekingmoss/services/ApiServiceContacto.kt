package com.thekingmoss.services

import retrofit2.Call
import com.thekingmoss.dto.contacto.ContactoRequestDto
import com.thekingmoss.dto.contacto.ContactoResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceContacto {

    @POST("/api/contacto")
    fun enviarMensaje(@Body request: ContactoRequestDto): Call<ContactoResponseDto>
}