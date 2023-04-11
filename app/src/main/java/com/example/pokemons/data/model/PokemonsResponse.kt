package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonsResponse (
    val success : Boolean,
    val records : MutableList<PokemonModel> = mutableListOf()
): Parcelable