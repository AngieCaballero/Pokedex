package com.example.pokemons.data

import android.util.Log
import com.example.pokemons.data.model.EntrenamientoPokemonModel
import com.example.pokemons.data.model.PerfilPokemonModel
import com.example.pokemons.data.model.PokemonAntSigModel
import com.example.pokemons.data.model.PokemonModel
import com.example.pokemons.data.network.PokemonsService
import javax.inject.Inject

class PokemonsRepository @Inject constructor(private val api: PokemonsService) {

    suspend fun getAllPokemons() : MutableList<PokemonModel> =
        api.getPokemons().records.toMutableList() ?: mutableListOf()

    suspend fun getPerfilPokemon(id : Int) : PerfilPokemonModel =
        api.getPerfilPokemon(id).pokemon?: PerfilPokemonModel()
}