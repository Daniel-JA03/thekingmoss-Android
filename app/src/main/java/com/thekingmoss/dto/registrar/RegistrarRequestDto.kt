package com.thekingmoss.dto.registrar

data class RegistrarRequestDto(
    val username: String,
    val password: String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val telefono: String,
    val email: String
)
