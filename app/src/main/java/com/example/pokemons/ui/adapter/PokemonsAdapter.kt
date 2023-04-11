package com.example.pokemons.ui.adapter

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.example.pokemons.data.model.PokemonModel

class PokemonsAdapter(private val Pokemons : MutableList<PokemonModel>, private val onClickListener:(PokemonModel) -> Unit) :
    RecyclerView.Adapter<PokemonsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonsHolder(layoutInflater.inflate(R.layout.card, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonsHolder, position: Int) {
        val item = Pokemons[position]
        return holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = Pokemons.size
}