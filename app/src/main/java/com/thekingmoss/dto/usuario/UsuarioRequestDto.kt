package com.thekingmoss.dto.usuario

data class UsuarioRequestDto(
    val username: String,
    val password: String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val telefono: String,
    val email: String,
    val roles: Set<String>
)
