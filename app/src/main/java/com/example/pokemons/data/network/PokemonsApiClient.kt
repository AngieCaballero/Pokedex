package com.example.pokemons.data.network

import com.example.pokemons.data.model.PokemonResponse
import com.example.pokemons.data.model.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonsApiClient {
    @GET("?accion=pokemonlistadonumerico")
    suspend fun getAllPokemons():Response<PokemonsResponse>

    @GET("?accion=pokemoninformaciongeneral")
    suspend fun getPokemon(@Query("numero") numero : Int):Response<PokemonResponse>
}