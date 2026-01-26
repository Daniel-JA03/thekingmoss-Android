package com.thekingmoss.controlador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thekingmoss.dto.contacto.ContactoRequestDto
import com.thekingmoss.dto.contacto.ContactoResponseDto
import com.thekingmoss.repository.ContactoRepository
import kotlinx.coroutines.launch

class ContactoViewModel(private val repository: ContactoRepository): ViewModel() {
    private val _envioResult = MutableLiveData<Result<ContactoResponseDto>>()
    val envioResult: LiveData<Result<ContactoResponseDto>> = _envioResult

    fun enviarMensaje(
        nombre: String,
        email: String,
        asunto: String?,
        mensaje: String
    ) {
        viewModelScope.launch {
            val request = ContactoRequestDto(
                nombre = nombre,
                email = email,
                asunto = if (asunto.isNullOrEmpty()) null else asunto,// Esto es más explícito y evita cualquier confusión del compilador
                mensaje = mensaje,
                estado = null // El backend lo maneja
            )
            _envioResult.value = repository.enviarMensaje(request)
        }
    }
}