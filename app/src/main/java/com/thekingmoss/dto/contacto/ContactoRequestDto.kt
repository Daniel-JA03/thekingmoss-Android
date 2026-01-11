package com.thekingmoss.dto.contacto

data class ContactoRequestDto(
    val nombre: String,
    val email: String,
    val asunto: String?,
    val mensaje: String,
    val estado: String?
)
