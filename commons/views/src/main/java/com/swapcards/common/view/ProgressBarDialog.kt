package com.swapcards.common.view

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.swapcards.common.view.databinding.ViewProgressDialogBinding
import com.swarcards.commons.ui.extensions.getString

class ProgressBarDialog(
    context: Context
) : AlertDialog(context, R.style.CustomProgressDialog) {


    lateinit var viewBinding: ViewProgressDialogBinding

    override fun show() {
        show(null)
    }

    fun show(@StringRes messageRes: Int?) {
        super.show()
        viewBinding = ViewProgressDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(viewBinding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        viewBinding.isLoading = true
        viewBinding.message = context.getString(messageRes)
    }

    fun dismissWithErrorMessage(@StringRes messageRes: Int) {
        setCanceledOnTouchOutside(true)
        setCancelable(true)

        viewBinding.isLoading = false
        viewBinding.message = context.getString(messageRes)
    }
}
