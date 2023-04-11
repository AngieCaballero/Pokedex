package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EvolucionesPokemonModel (
    val etapa : Int = 0,
    val nivel : String = "",
    val numero_nacional : Int = 0,
    val nombre_ingles : String = "",
    val comentarios : String = ""
): Parcelable