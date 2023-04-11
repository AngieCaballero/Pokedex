package com.example.pokemons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat.Type.*
import androidx.core.view.updateLayoutParams
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.pokemons.BR

abstract class BaseFragment<B: ViewBinding>: Fragment() {

    companion object {
        const val BUNDLE_ARGS = "COM_POKEDEX_BUNDLE_ARGS"
    }

    private var _binding: B? = null
    val binding: B
        get() = _binding!!
    val nullableBinding: B?
        get() = _binding

    protected var savedInsetBottom: Int = 0
    protected open val viewModel: BaseViewModel? = null
    protected open var showBottomNav: Boolean = false
    protected open var actionForAfterApplyingWindowInsets: (() -> Unit)? = null

    override fun onResume() {
        super.onResume()
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView(inflater, container)
        return binding.root.also { setupLayoutInsets() }
    }

    abstract fun onCreateBinding(inflater: LayoutInflater): B

    @CallSuper
    protected open fun bindView(inflater: LayoutInflater, container: ViewGroup?): B {
        return onCreateBinding(inflater).apply {
            if (this is ViewDataBinding) {
                lifecycleOwner = viewLifecycleOwner
                viewModel?.let { setVariable(BR.viewModel, it) }
            }
        }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun setupLayoutInsets() {
        setMarginInset(binding.root)
    }

    protected open fun shouldShowBottomNav(): Boolean {
        return showBottomNav
    }

    protected open fun setMarginInset(container: View) {
        ViewCompat.setOnApplyWindowInsetsListener(container) { view, windowInsets ->
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                val insets = windowInsets.getInsets(systemBars() or ime())
                savedInsetBottom = insets.bottom

                leftMargin = insets.left
                topMargin = insets.top
                rightMargin = insets.right
                bottomMargin = if (!shouldShowBottomNav()) insets.bottom else 0
            }
            callActionForAfterApplyingWindowInsets()
            windowInsets
        }
    }

    override fun setArguments(args: Bundle?) {
        if (args != null){
            val curBundle = args.getBundle(BUNDLE_ARGS)
            val newBundle = curBundle?.apply {
                putAll(args)
                remove(BUNDLE_ARGS)
            } ?: args

            super.setArguments(Bundle(args).apply {
                putBundle(BUNDLE_ARGS, newBundle)
            })
        } else {
            super.setArguments(null)
        }
    }

    private fun callActionForAfterApplyingWindowInsets() {
        actionForAfterApplyingWindowInsets?.invoke()
        actionForAfterApplyingWindowInsets = null
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let { onRestoreInstanceState(it) }
    }

    open fun onRestoreInstanceState(savedInstanceState: Bundle) {
        viewModel?.let { vm ->
            savedInstanceState.getBundle(vm::class.qualifiedName)?.let(vm::restoreInstanceState)
        }
    }

    open fun getOptionsForSaveInstanceState(): Bundle = bundleOf()

    open fun onSaveViewModelInstanceState(outState: Bundle) {
        viewModel?.let { vm ->
            outState.putBundle(
                vm::class.qualifiedName,
                vm.saveInstanceState(getOptionsForSaveInstanceState())
            )
        }
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        onSaveViewModelInstanceState(outState)
    }
}