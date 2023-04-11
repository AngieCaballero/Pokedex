package com.example.pokemons.ui.base

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    open fun saveInstanceState(options: Bundle? = null): Bundle = bundleOf()

    open fun restoreInstanceState(savedInstanceState: Bundle) {}
}