package com.example.pokemons.domain

import com.example.pokemons.data.PokemonsRepository
import com.example.pokemons.data.model.PokemonModel
import javax.inject.Inject

class PokemonsList @Inject constructor(private val repository: PokemonsRepository) {

    suspend operator fun invoke() : MutableList<PokemonModel> = repository.getAllPokemons()
}