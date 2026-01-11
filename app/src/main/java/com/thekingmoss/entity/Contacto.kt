package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Contacto(
    val nombre: String,
    val email: String,
    val asunto: String?,
    val mensaje: String
) : Parcelable { }