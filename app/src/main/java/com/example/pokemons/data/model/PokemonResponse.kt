package com.example.pokemons.data.model

data class PokemonResponse (
    val success : Boolean,
    val pokemon : PerfilPokemonModel? = null
)