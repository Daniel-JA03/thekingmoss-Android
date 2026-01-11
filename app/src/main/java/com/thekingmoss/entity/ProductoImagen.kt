package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductoImagen(
    val productoImagenId: Long,
    val imagenUrl: String
) : Parcelable { }