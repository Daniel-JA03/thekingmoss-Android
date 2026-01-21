package com.thekingmoss.services

import com.thekingmoss.dto.registrar.RegistrarRequestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceRegistrar {

    @POST("/api/auth/register")
    fun register(
        @Body request: RegistrarRequestDto
    ): Call<Map<String, String>>
}