package com.thekingmoss.utils

import com.thekingmoss.services.ApiServiceCarrito
import com.thekingmoss.services.ApiServiceLogin
import com.thekingmoss.services.ApiServicePedido
import com.thekingmoss.services.ApiServiceProducto
import com.thekingmoss.services.ApiServiceProductoImagen

class ApiUtils {
    companion object {
        val BASE_URL = "http://192.168.100.16:8080"

        fun authService(): ApiServiceLogin {
            return RetrofitClient.getClient(BASE_URL)
                .create(ApiServiceLogin::class.java)
        }

        fun carritoService(token: String): ApiServiceCarrito {
            return RetrofitClient
                .getClient(BASE_URL, token)
                .create(ApiServiceCarrito::class.java)
        }
        //No lo borren porfaaaaaaaaaaaa
        fun pedidoService(token: String): ApiServicePedido {
            return RetrofitClient
                .getClient(BASE_URL, token)
                .create(ApiServicePedido::class.java)
        }

        fun productoService() : ApiServiceProducto {
            return RetrofitClient.getClient(BASE_URL)
                .create(ApiServiceProducto::class.java)
        }

        fun productoImagenService() : ApiServiceProductoImagen {
            return RetrofitClient.getClient(BASE_URL)
                .create(ApiServiceProductoImagen::class.java)
        }


    }
}