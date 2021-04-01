package com.goodchoice.android.loalending.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.ToastSuccessBinding
import com.goodchoice.android.loalending.databinding.ToastWarningBinding
import timber.log.Timber

class CustomToast(private val context: Context, private val message: String) {
    companion object {
        private var toast: Toast? = null
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var warningBinding: ToastWarningBinding = DataBindingUtil.inflate(
        inflater,
        R.layout.toast_warning,
        null,
        false
    )

    fun warningToast(): Toast {
        toast?.cancel()
        warningBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.toast_warning,
            null,
            false
        )
//        binding.toastClose.setOnClickListener {
//            toast!!.cancel()
//        }
        toast = Toast(context).apply {
            setGravity(Gravity.END or Gravity.TOP, 0, (90).dpToPx())
            duration = Toast.LENGTH_SHORT
            view = warningBinding.root
        }
        warningBinding.toastText.text = message
        return toast!!
    }

    fun successToast(): Toast {
        toast?.cancel()
        val successBinding: ToastSuccessBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.toast_success,
            null,
            false
        )
        toast = Toast(context).apply {
            setGravity(Gravity.END or Gravity.TOP, 0, (90).dpToPx())
            duration = Toast.LENGTH_SHORT
            view = successBinding.root
        }
        successBinding.toastText.text = message
        return toast!!
    }
}