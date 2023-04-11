package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class HabilidadesPokemonModel(
    val nombre : String = "",
    val nombre_ingles : String = "",
    val descripcion : String = "",
    val es_oculta : Boolean = false
): Parcelable