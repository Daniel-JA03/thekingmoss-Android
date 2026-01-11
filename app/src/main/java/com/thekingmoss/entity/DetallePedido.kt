package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DetallePedido(
    val producto: ProductoResumen,
    val cantidad: Int
) : Parcelable { }