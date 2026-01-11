package com.thekingmoss.dto.documentoIdentidad

data class DocumentoIdentidadResponseDto(
    val documentoIdentidadId: Long,
    val numeroDocumentoIdentidad: String,
    val tipoDocumentoIdentidad: String,
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
