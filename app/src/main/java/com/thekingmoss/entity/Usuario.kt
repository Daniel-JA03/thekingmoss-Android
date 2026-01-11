package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Usuario(
    val id: Long,
    val username: String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val telefono: String,
    val email: String,
    val roles: List<Rol>
) : Parcelable {}