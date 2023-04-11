package com.example.pokemons.ui.viewModel

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.pokemons.data.model.PerfilPokemonModel
import com.example.pokemons.ui.base.BaseViewModel
import java.text.Normalizer

class MainViewModel : BaseViewModel() {

    companion object {
        const val NAVIGATION_RESTART_ACTIVITY = "navigationRestartActivity"
    }

    var restartActivity = false

    var pressedBackFromPokemonFragment : Boolean = false

    var pokemonSelected: PerfilPokemonModel? = null

    fun clearAccents(cadena : String): String {
        var cadenaNormalizer = Normalizer.normalize(cadena.lowercase(), Normalizer.Form.NFD)
        var c = cadenaNormalizer.replace("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]".toRegex(), "")
        return Normalizer.normalize(c, Normalizer.Form.NFC)
    }

    override fun saveInstanceState(options: Bundle?): Bundle {
        return bundleOf()
    }

    fun saveInstanceState(
        restartActivity: Boolean = false
    ): Bundle {
        val bundle = bundleOf(NAVIGATION_RESTART_ACTIVITY to restartActivity)
        return saveInstanceState(bundle)
    }

    override fun restoreInstanceState(savedInstanceState: Bundle) {
        restartActivity = savedInstanceState.getBoolean(NAVIGATION_RESTART_ACTIVITY)
    }
}