package com.goodchoice.android.loalending.ui.loan

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.DialogCancelBinding
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.ui.apply.ApplyViewModel
import org.koin.android.ext.android.inject

class ApplyCancelDialog(private val apply:Apply) : DialogFragment() {

    private lateinit var binding: DialogCancelBinding
    private val applyViewModel: ApplyViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        applyViewModel.lastApply=apply
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_cancel,
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 확인버튼 클릭
        binding.dialogCancelOk.setOnClickListener {
            applyViewModel.applyCancel(apply.seq.toString())
        }

        // 취소버튼 클릭
        binding.dialogCancelX.setOnClickListener {
            dismiss()
        }

        applyViewModel.applyCancelCheck.observe(viewLifecycleOwner, {
            if (it) {
                applyViewModel.applyCancelCheck.postValue(false)
                dismiss()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        // dialogFragment 크기조절
        val windowManager =
            requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        size.x // 디바이스 가로 길이
        size.y // 디바이스 세로 길이
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.95).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}