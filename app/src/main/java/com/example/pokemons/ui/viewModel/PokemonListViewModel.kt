package com.example.pokemons.ui.viewModel

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.data.model.PerfilPokemonModel
import com.example.pokemons.data.model.PokemonModel
import com.example.pokemons.domain.PokemonInfo
import com.example.pokemons.domain.PokemonsList
import com.example.pokemons.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonsList: PokemonsList,
    private val pokemonInfo: PokemonInfo
) : BaseViewModel() {

    private val mPokemonList = MutableLiveData<MutableList<PokemonModel>>()
    val pokemonList : MutableLiveData<MutableList<PokemonModel>>
        get() = mPokemonList

    var searchedPokemon = MutableLiveData<MutableList<PokemonModel>>()
    val isLoading = MutableLiveData<Boolean>()

    private val mPokemon = MutableLiveData<PerfilPokemonModel?>()
    val pokemon : MutableLiveData<PerfilPokemonModel?>
        get() = mPokemon

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = pokemonsList()

            if (!result.isNullOrEmpty()) {
                mPokemonList.postValue(result)
                isLoading.postValue(false)
            }
            else isLoading.postValue(false)
        }
    }

    fun getPerfilPokemon(id : Int){
        isLoading.postValue(true)

        viewModelScope.launch {
            val result = pokemonInfo(id)
            if (result.numero != 0){
                mPokemon.postValue(result)
                isLoading.postValue(false)
            }
            else isLoading.postValue(false)
        }
    }

    fun searchPokemonByName(name: CharSequence){
        val pokemonSearch : MutableList<PokemonModel> = mutableListOf()
        mPokemonList.value?.forEach {
            if (it.nombre.lowercase().contains(name)) pokemonSearch.add(it)
        }
        mPokemonList.value = pokemonSearch
    }

    fun clearPokemon(){
        mPokemon.value = null
    }

    override fun saveInstanceState(options: Bundle?): Bundle {
        return bundleOf(
            "currentPokemonList" to mPokemonList.value
        )
    }

    override fun restoreInstanceState(savedInstanceState: Bundle) {
        mPokemonList.value = savedInstanceState.getParcelableArrayList("currentPokemonList")
        Log.i("Restore", "${mPokemonList.value}")
    }
}