package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel (
    val numero : Int = 0,
    val nombre : String = "",
    val tipo_1 : String = "",
    val tipo_2 : String = "",
    val peso : String = "",
    val altura : String = ""
) : Parcelable