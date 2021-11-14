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
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    protected abstract val viewModel: VM

    private lateinit var _viewBinding: VB
    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _viewBinding as VB

    abstract fun prepareView()
    abstract fun onInitDataBinding()
    abstract fun observe()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return _viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        onInitDataBinding()
        observe()
    }


}
