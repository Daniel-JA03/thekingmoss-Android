package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductoResumen(
    val productoId: Long,
    val nombre: String,
    val precioUnitario: Double,
    val imagenUrl: String?
) : Parcelable { }