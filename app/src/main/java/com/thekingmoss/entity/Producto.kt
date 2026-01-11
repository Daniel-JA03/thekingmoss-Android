package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Producto(
    val productoId: Long,
    val nombre: String,
    val stock: Int,
    val precioUnitario: Double,
    val descuento: Double,
    val descripcion: String?,
    val imagenes: List<ProductoImagen>
) : Parcelable { }