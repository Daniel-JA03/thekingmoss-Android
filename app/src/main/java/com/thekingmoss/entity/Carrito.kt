package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Carrito(
    val idCarrito: Long,
    val producto: ProductoResumen,
    val cantidad: Int
) : Parcelable { }