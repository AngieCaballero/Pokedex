package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EntrenamientoPokemonModel (
    val ratio_captura : Int = 0,
    val felicidad_base : Int = 0,
    val experiencia_base : Int = 0,
    val velocidad_crecimiento : String = ""
): Parcelable