package com.thekingmoss.dto.usuario

data class UsuarioResponseDto(
    val id: Long,
    val username: String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val telefono: String,
    val email: String,
    val roles: List<String>
)
