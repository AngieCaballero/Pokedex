package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PokemonAntSigModel (
    val numero : Int = 0,
    val nombre : String = ""
): Parcelable