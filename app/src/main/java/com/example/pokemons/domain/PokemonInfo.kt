package com.example.pokemons.domain

import com.example.pokemons.data.PokemonsRepository
import com.example.pokemons.data.model.PerfilPokemonModel
import javax.inject.Inject

class PokemonInfo @Inject constructor(private val repository: PokemonsRepository) {

    suspend operator fun invoke(id : Int) : PerfilPokemonModel = repository.getPerfilPokemon(id)
}