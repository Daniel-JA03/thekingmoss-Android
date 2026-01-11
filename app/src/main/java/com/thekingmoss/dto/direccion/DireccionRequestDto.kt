package com.thekingmoss.dto.direccion

data class DireccionRequestDto(
    val pais: String,
    val estado: String,
    val provincia: String,
    val distrito: String,
    val referencia: String,
    val tipoDireccion: String,
    val usuarioId: Long
)
