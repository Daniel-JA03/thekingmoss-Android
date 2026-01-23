package com.thekingmoss.services

import com.thekingmoss.dto.auth.LoginRequestDto
import com.thekingmoss.dto.auth.LoginResponseDto
import com.thekingmoss.dto.registrar.RegistrarRequestDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceLogin {

    @POST("/api/auth/login")

    fun login(@Body request: LoginRequestDto): Call<LoginResponseDto>

    @POST("/api/auth/register")
    fun register(@Body request: RegistrarRequestDto): Call<Map<String, String>>

}