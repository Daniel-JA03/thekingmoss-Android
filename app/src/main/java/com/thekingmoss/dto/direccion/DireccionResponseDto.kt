package com.thekingmoss.dto.direccion

data class DireccionResponseDto(
    val direccionId: Long,
    val pais: String,
    val estado: String,
    val provincia: String,
    val distrito: String,
    val referencia: String,
    val tipoDireccion: String,
    // Usuario
    val usuarioId: Long,
    val username: String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val telefono: String,
    val email: String,
    // Rol
    val nombreRoles: List<String>
)
