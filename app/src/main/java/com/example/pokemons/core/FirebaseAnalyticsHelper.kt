package com.example.pokemons.core

import android.os.Bundle
import android.util.Log
import com.example.pokemons.data.model.enums.AnalyticsEnum
import com.example.pokemons.data.model.enums.AnalyticsPokemons
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

object FirebaseAnalyticsHelper {

    fun getClickOnPokemon(
        pokemonId: String,
        pokemonName: String
    ) : Bundle {
        return Bundle().apply {
            putString(AnalyticsPokemons.ID_POKEMON.value, pokemonId)
            putString(AnalyticsPokemons.POKEMON.value, pokemonName)
        }
    }

    fun logEvent(
        firebaseAnalytics: FirebaseAnalytics,
        bundle: Bundle,
        event : String = FirebaseAnalytics.Event.SCREEN_VIEW
    ){
        Timber.d("$FIREBASE_ANALYTICS_LOG_TAG $event, Data: $bundle")
        firebaseAnalytics.logEvent(event, bundle)
    }

    private fun Bundle.putString(enum: AnalyticsEnum, value: String?){
        putString(enum.value, value)
    }

    private const val FIREBASE_ANALYTICS_LOG_TAG = "Firebase event:"
}