package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EstatusPokemonModel (
    val codigo : String = "",
    val nombre : String = "",
    val valor_base : Int = 0,
    val valor_maximo_pokedex : Int = 0,
    val valor_effort : Int = 0
): Parcelable