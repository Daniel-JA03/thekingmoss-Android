package com.thekingmoss.repository

import retrofit2.HttpException
import com.thekingmoss.dto.contacto.ContactoRequestDto
import com.thekingmoss.dto.contacto.ContactoResponseDto
import com.thekingmoss.services.ApiServiceContacto
import java.io.IOException

class ContactoRepository(private val apiServiceContacto: ApiServiceContacto) {
    suspend fun enviarMensaje(request: ContactoRequestDto): Result<ContactoResponseDto> {
        return try {
            val response = apiServiceContacto.enviarMensaje(request).execute()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}