package com.example.pokemons.ui.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.data.model.PokemonModel
import com.example.pokemons.databinding.CardBinding
import java.text.Normalizer

class PokemonsHolder(view : View) : RecyclerView.ViewHolder(view) {
    val binding = CardBinding.bind(view)

    fun render(Pokemon : PokemonModel, onClickListener:(PokemonModel) -> Unit)
    {
        binding.tvNamePokemon.text = Pokemon.nombre
        binding.tvPeso.text = "Peso: " + Pokemon.peso
        binding.tvAltura.text = "Altura: " + Pokemon.altura

        Glide.with(binding.ivTipo1.context)
            .load("https://pokefanaticos.com/pokedex/assets/images/pokemon_tipos/tipo_${clearAccents(Pokemon.tipo_1)}.gif")
            .into(binding.ivTipo1)
        if (Pokemon.tipo_2 == "Ninguno"){
            binding.ivTipo2.visibility = View.INVISIBLE
        }
        else{
            Glide.with(binding.ivTipo2.context)
                .load("https://pokefanaticos.com/pokedex/assets/images/pokemon_tipos/tipo_${clearAccents(Pokemon.tipo_2)}.gif")
                .into(binding.ivTipo2)
        }
        Glide.with(binding.ivPokemon.context)
            .load("https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/${Pokemon.numero}.png")
            .into(binding.ivPokemon)

        binding.cvItem.setOnClickListener{

            onClickListener(Pokemon)
            Log.i("Pokemon selected holder", "${Pokemon}")
        }
    }

    fun clearAccents(cadena : String): String {
        var cadenaNormalizer = Normalizer.normalize(cadena.lowercase(), Normalizer.Form.NFD)
        var c = cadenaNormalizer.replace("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]".toRegex(), "")
        return Normalizer.normalize(c, Normalizer.Form.NFC)
    }

}