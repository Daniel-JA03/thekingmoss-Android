package com.thekingmoss.controlador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thekingmoss.dto.registrar.RegistrarRequestDto
import com.thekingmoss.repository.RegistroRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class RegistroViewModel (private val repository: RegistroRepository): ViewModel(){
    private val _registerResult = MutableLiveData<Result<Map<String, String>>>()
    val registerResult: LiveData<Result<Map<String, String>>> = _registerResult


    fun register(
        username: String,
        password: String,
        nombre: String,
        apellido: String,
        telefono: String,
        email: String
    ){
        viewModelScope.launch {
            val request = RegistrarRequestDto(
                username = username,
                password = password,
                nombreUsuario = nombre,
                apellidoUsuario = apellido,
                telefono = telefono,
                email = email
            )
            _registerResult.value = repository.register(request)
     }
   }
}