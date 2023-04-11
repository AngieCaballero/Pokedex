package com.example.pokemons.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.pokemons.data.model.PerfilPokemonModel
import com.example.pokemons.databinding.PokemonFragmentBinding
import com.example.pokemons.ui.base.BaseFragment
import com.example.pokemons.ui.viewModel.MainViewModel
import com.example.pokemons.ui.viewModel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.Normalizer

@AndroidEntryPoint
class PokemonFragment : BaseFragment<PokemonFragmentBinding>() {

    override val viewModel by activityViewModels<PokemonListViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var Pokemon : PerfilPokemonModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            Pokemon = it.getParcelable<PerfilPokemonModel>("pokemon")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateBinding(inflater: LayoutInflater): PokemonFragmentBinding {
        return PokemonFragmentBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPokemonView()
    }

    fun initPokemonView(){
        binding.tvNombre.text = Pokemon.nombre
        binding.tvDescripcion.text = Pokemon.descripcion
        binding.tvNumNacional.text = Pokemon.numero.toString()
        binding.tvNombreInfo.text = Pokemon.nombre
        binding.tvPesoInfo.text = Pokemon.peso.toString()
        binding.tvAlturaInfo.text = Pokemon.altura.toString()
        binding.tvColorInfo.text = Pokemon.color
        binding.tvFormaInfo.text = Pokemon.forma
        binding.tvHabitadInfo.text = Pokemon.habitat
        binding.tvClasificacionInfo.text = Pokemon.clasificacion
        Glide.with(binding.ivTipo1Info.context)
            .load("https://pokefanaticos.com/pokedex/assets/images/pokemon_tipos/tipo_${mainViewModel.clearAccents(Pokemon.tipo_1)}.gif")
            .into(binding.ivTipo1Info)

        if (Pokemon.tipo_2 == "Ninguno"){
            binding.ivTipo2Info.visibility = View.INVISIBLE
        }
        else{
            Glide.with(binding.ivTipo2Info.context)
                .load("https://pokefanaticos.com/pokedex/assets/images/pokemon_tipos/tipo_${mainViewModel.clearAccents(Pokemon.tipo_2)}.gif")
                .into(binding.ivTipo2Info)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).setImagePokeball()
    }
}

