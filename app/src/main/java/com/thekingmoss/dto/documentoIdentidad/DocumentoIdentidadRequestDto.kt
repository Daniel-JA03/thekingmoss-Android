package com.thekingmoss.dto.documentoIdentidad

data class DocumentoIdentidadRequestDto(
    val numeroDocumentoIdentidad: String,
    val tipoDocumentoIdentidad: String,
    val usuarioId: Long
)
