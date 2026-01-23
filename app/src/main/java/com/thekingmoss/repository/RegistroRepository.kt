
package com.thekingmoss.repository

import retrofit2.HttpException
import java.io.IOException
import com.thekingmoss.dto.registrar.RegistrarRequestDto
import com.thekingmoss.services.ApiServiceLogin

class RegistroRepository(private val apiServiceLogin: ApiServiceLogin) {

    suspend fun register(request: RegistrarRequestDto): Result<Map<String, String>> {
        return try {
            val response = apiServiceLogin.register(request).execute()
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