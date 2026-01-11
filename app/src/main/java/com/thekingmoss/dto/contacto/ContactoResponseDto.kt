package com.thekingmoss.dto.contacto

data class ContactoResponseDto(
    val contactoid: Long,
    val nombre: String,
    val email: String,
    val asunto: String?,
    val mensaje: String,
    val estado: String,
    val fechaCreacion: String
)
