package com.example.pokemons.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PerfilPokemonModel (
    val numero : Int = 0,
    val nombre :  String = "",
    val tipo_1 : String = "Ninguno",
    val tipo_2 : String = "Ninguno",
    val peso : Double = 0.0,
    val altura : Double = 0.0,
    val color : String = "",
    val forma : String = "",
    val habitat : String = "",
    val clasificacion : String = "",
    val descripcion : String = "",
    val evoluciones : MutableList<EvolucionesPokemonModel> = mutableListOf(),
    val habilidades : MutableList<HabilidadesPokemonModel> = mutableListOf(),
    val entrenamiento : EntrenamientoPokemonModel? = null,
    val estatus : MutableList<EstatusPokemonModel> = mutableListOf(),
    val crianza : String = "",
    val pokedex_pokemon_anterior : PokemonAntSigModel? = null,
    val pokedex_pokemon_siguiente : PokemonAntSigModel? = null
) : Parcelable
