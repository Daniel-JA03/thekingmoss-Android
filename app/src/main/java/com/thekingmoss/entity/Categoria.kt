package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Categoria(
    var categoriaId: Long,
    var nombreCategoria: String
) : Parcelable { }