package com.swarcards.commons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    protected abstract val viewModel: VM

    private lateinit var _viewBinding: VB
    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _viewBinding as VB

    abstract fun prepareView()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _viewBinding.lifecycleOwner = viewLifecycleOwner
        return _viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }


}
