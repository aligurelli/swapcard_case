/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
