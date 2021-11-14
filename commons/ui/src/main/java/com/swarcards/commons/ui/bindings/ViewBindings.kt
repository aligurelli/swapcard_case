package com.swarcards.commons.ui.bindings

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindings {

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}