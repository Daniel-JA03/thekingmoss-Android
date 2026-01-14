package com.thekingmoss.utils

import com.thekingmoss.services.ApiServiceCarrito

class ApiUtils {
    companion object {
        val BASE_URL = "http://192.168.100.16:8080"

        fun carritoService(token: String): ApiServiceCarrito {
            return RetrofitClient
                .getClient(BASE_URL, token)
                .create(ApiServiceCarrito::class.java)
        }
    }
}