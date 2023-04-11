package com.example.pokemons.ui.view

import android.view.LayoutInflater
import com.example.pokemons.databinding.MyFavsFragmentBinding
import com.example.pokemons.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavsFragment : BaseFragment<MyFavsFragmentBinding>() {

    override fun onCreateBinding(inflater: LayoutInflater): MyFavsFragmentBinding {
        return MyFavsFragmentBinding.inflate(inflater)
    }

}