package com.example.pokemons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.pokemons.BR
import com.example.pokemons.ui.viewModel.MainViewModel

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private var _binding: B? = null

    val binding: B
        get() = _binding!!

    protected open val viewModel: ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindView(layoutInflater).apply { setContentView(root) }
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B

    @CallSuper
    protected open fun bindView(inflater: LayoutInflater): B {
        return onCreateBinding(inflater).apply {
            if (this is ViewDataBinding) {
                lifecycleOwner = this@BaseActivity
                viewModel?.let { setVariable(BR.viewModel, it) }
            }
        }
    }
}