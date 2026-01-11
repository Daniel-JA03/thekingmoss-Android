package com.thekingmoss.dto.auth

data class LoginResponseDto(
    val token: String,
    val username: String,
    val email: String,
    val roles: List<String>,
    val usuarioId: Long,
    val expirateAt: Long
)
