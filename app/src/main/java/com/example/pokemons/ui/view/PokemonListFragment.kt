package com.example.pokemons.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemons.core.FirebaseAnalyticsHelper
import com.example.pokemons.core.safeNavigate
import com.example.pokemons.data.model.PokemonModel
import com.example.pokemons.databinding.PokemonslistFragmentBinding
import com.example.pokemons.ui.adapter.PokemonsAdapter
import com.example.pokemons.ui.base.BaseFragment
import com.example.pokemons.ui.viewModel.MainViewModel
import com.example.pokemons.ui.viewModel.PokemonListViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment() : BaseFragment<PokemonslistFragmentBinding>(), SearchView.OnQueryTextListener {

    override val viewModel by activityViewModels<PokemonListViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var pokemons : MutableList<PokemonModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(this)
        setObserves()
        if (savedInstanceState == null && !mainViewModel.pressedBackFromPokemonFragment){
            viewModel.onCreate()
        }

        mainViewModel.pressedBackFromPokemonFragment = false
    }

    override fun onCreateBinding(inflater: LayoutInflater): PokemonslistFragmentBinding {
        return PokemonslistFragmentBinding.inflate(inflater)
    }

    private fun setObserves(){
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                pokemons.clear()
                pokemons.addAll(it)
                initRecyclerView(pokemons)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.ProgressBar.isVisible = it
        }
    }

    private fun initRecyclerView(PokemonsList : MutableList<PokemonModel>) {
        val manager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = PokemonsAdapter(PokemonsList){
            onPokemonSelected(it)
        }
    }

    private fun searchByName(name: CharSequence){
        viewModel.searchPokemonByName(name)
    }

    private fun onPokemonSelected(Pokemon : PokemonModel){
        viewModel.getPerfilPokemon(Pokemon.numero)

        viewModel.pokemon.observe(viewLifecycleOwner) {
            if (it != null){
                mainViewModel.pokemonSelected = it
                handleAnalytics()

                try{
                    mainViewModel.pressedBackFromPokemonFragment = true
                    (requireActivity() as? MainActivity)?.setImagePokemon(it.numero.toString(), mainViewModel.clearAccents(it.color))
                    safeNavigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonFragment(it))
                }
                catch(e: Exception) {
                    Log.i("Error", e.toString())
                }
            }
        }
    }

    private fun handleAnalytics(){
        if(!this::firebaseAnalytics.isInitialized){
            firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        }

        val pokemonBundle = FirebaseAnalyticsHelper.getClickOnPokemon(
            pokemonId = mainViewModel.pokemonSelected?.numero.toString(),
            pokemonName = mainViewModel.pokemonSelected?.nombre ?: "-"
        )

        FirebaseAnalyticsHelper.logEvent(firebaseAnalytics, pokemonBundle)
    }

    override fun onQueryTextSubmit(nombre: String?): Boolean {
        if (!nombre.isNullOrEmpty()) {
            searchByName(nombre.lowercase())
            (requireActivity() as MainActivity).hideKeyboard()
        }
        return true
    }

    override fun onQueryTextChange(nombre: String?): Boolean {
        if (!nombre.isNullOrEmpty()) searchByName(nombre.lowercase())
        else viewModel.onCreate()

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearPokemon()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}