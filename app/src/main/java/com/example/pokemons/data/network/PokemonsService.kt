package com.example.pokemons.data.network

import com.example.pokemons.data.model.PokemonResponse
import com.example.pokemons.data.model.PokemonsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonsService @Inject constructor(private val api: PokemonsApiClient) {

    suspend fun getPokemons() : PokemonsResponse {
        return withContext(Dispatchers.IO) {
            val response : Response<PokemonsResponse> = api.getAllPokemons()
            response.body()!!
        }
    }

    suspend fun getPerfilPokemon(id : Int) : PokemonResponse {
        return withContext(Dispatchers.IO) {
            val response : Response<PokemonResponse> = api.getPokemon(id)
            response.body()!!
        }
    }
}