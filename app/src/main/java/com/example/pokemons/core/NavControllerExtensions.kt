package com.example.pokemons.core

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.R
import com.example.pokemons.ui.view.MainActivity
import timber.log.Timber

fun getNavOptionsWithAnimations(): NavOptions {
    return navOptions {
        anim {
            enter = R.anim.nav_default_enter_anim
            exit = R.anim.nav_default_exit_anim
            popEnter = R.anim.nav_default_pop_enter_anim
            popExit = R.anim.nav_default_pop_exit_anim
        }
    }
}

fun NavController.safeNavigate(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null
){
    try {
        val options = navOptions ?: getNavOptionsWithAnimations()
        navigate(resId, args, options)
    } catch (e: IllegalArgumentException){
        Timber.e(e)
    }
}

fun NavController.safeNavigate(directions: NavDirections, navOptions: NavOptions? = null){
    try {
        val options = navOptions ?: getNavOptionsWithAnimations()
        navigate(directions, options)
    } catch (e: IllegalArgumentException){
        Timber.e(e)
    }
}

fun Fragment.safeNavigate(directions: NavDirections, navOptions: NavOptions? = null){
    (requireActivity() as? MainActivity)?.clearOnBackPressed()
    findNavController().safeNavigate(directions, navOptions)
}