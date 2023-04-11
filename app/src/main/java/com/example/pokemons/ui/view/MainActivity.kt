package com.example.pokemons.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.ui.base.BaseActivity
import com.example.pokemons.ui.viewModel.MainViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController
    private var onFragmentBackPressed: (() -> Boolean)? = null
    private var callSuperOnBackPressed = true

    private val nav : NavigationView
        get() = binding.nav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) restoreInstanceState(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.mainInclude.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController

        nav.setNavigationItemSelectedListener(this)

        setListeners()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_pokemonListFragment -> {

            }
            R.id.item_favoriteFragment -> {

            }
        }
        return true
    }

    fun setListeners(){
        binding.mainInclude.buttonBack?.setOnClickListener {
            setImagePokeball()
            navController.popBackStack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(
            MainViewModel::class.qualifiedName,
            viewModel.saveInstanceState(restartActivity = false)
        )
    }

    fun restoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.getBundle(MainViewModel::class.qualifiedName)?.let(viewModel::restoreInstanceState)
    }

    override fun onCreateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun setImagePokemon(image: String, background: String){
        Glide.with(binding.mainInclude.ivPokemobola)
            .load("https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/${image}.png")
            .into(binding.mainInclude.ivPokemobola)

        var color: Int
        when(background){
            "rojo" -> color = R.color.Rojo
            "verde" -> color = R.color.Verde
            "purpura" -> color = R.color.Purpura
            "negro" -> color = R.color.Negro
            "amarillo" -> color = R.color.Amarillo
            "azul" -> color = R.color.Azul
            "gris" -> color = R.color.Gris
            "rosa" -> color = R.color.Rosa
            "marron" -> color = R.color.Marron
            "blanco" -> color = R.color.Blanco
            else -> color = R.color.Verde
        }
        binding.mainInclude.RelativeLayout?.background = getDrawable(color)
        binding.mainInclude.buttonBack?.isVisible = true
    }

    fun setImagePokeball() {
        binding.mainInclude.ivPokemobola.setImageResource(R.drawable.pokebola)
        binding.mainInclude.RelativeLayout?.background = getDrawable(R.color.Verde)
        binding.mainInclude.buttonBack?.isVisible = false
    }

    fun setOnBackPressed(action: (() -> Boolean)?, callOnBackPressed: Boolean) {
        onFragmentBackPressed = action
        callSuperOnBackPressed = callOnBackPressed
    }

    fun clearOnBackPressed() {
        setOnBackPressed(action = null, callOnBackPressed = true)
    }

    fun navigateBack(onCLickBack: (() -> Unit)){

    }

    override fun onBackPressed() {
        val success: Boolean = onFragmentBackPressed?.invoke() ?: true
        val callSuper = this.callSuperOnBackPressed
        // if we "navigated"
        if (success || callSuper) {
            clearOnBackPressed()
        }
        if (callSuper) super.onBackPressed()
    }

    fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.mainInclude.idRoot.windowToken, 0)
    }
}