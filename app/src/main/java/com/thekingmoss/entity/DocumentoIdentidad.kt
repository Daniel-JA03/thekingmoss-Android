package com.thekingmoss.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DocumentoIdentidad(
    val documentoIdentidadId: Long,
    val numeroDocumentoIdentidad: String,
    val tipoDocumentoIdentidad: String
) : Parcelable { }