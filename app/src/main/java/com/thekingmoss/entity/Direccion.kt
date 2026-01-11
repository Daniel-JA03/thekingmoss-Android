package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Direccion(
    val direccionId: Long,
    val pais: String,
    val estado: String,
    val provincia: String,
    val distrito: String,
    val referencia: String,
    val tipoDireccion: String
) : Parcelable { }