package com.goodchoice.android.loalending.ui.loan

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.DialogApplyDetailBinding
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.ui.apply.ApplyViewModel
import com.goodchoice.android.loalending.util.CustomToast
import com.goodchoice.android.loalending.util.comma
import com.goodchoice.android.loalending.util.constant.Code
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import org.koin.android.ext.android.inject
import java.util.jar.Manifest

class ApplyListDetailDialog(private val apply: Apply) : DialogFragment() {

    private lateinit var binding: DialogApplyDetailBinding
    private val applyViewModel: ApplyViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NO_FRAME, R.style.)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_apply_detail,
            null,
            false
        )
        val commaApply = apply
        if (commaApply.maxLoan.isNullOrEmpty()) {
            commaApply.maxLoan = "0"
        } else {
            commaApply.maxLoan = comma(commaApply.maxLoan!!.replace(",", "").toLong())

        }
        commaApply.hopeLoan = comma(commaApply.hopeLoan.replace(",", "").toLong())
        binding.apply = commaApply
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyViewModel.lastApply=apply
        // x버튼 누를때
        binding.dialogApplyDetailClose.setOnClickListener {
            dismiss()
        }

        //전화 문의 클릭할때
        binding.dialogApplyDetailCall.setOnClickListener {
            val number="025976778"
            val callUri=Uri.parse("tel:${number}")
            val callIntent=Intent(Intent.ACTION_DIAL,callUri)
            startActivity(callIntent)

        }

        // 신청취소 누를때
        binding.dialogApplyDetailCancel.setOnClickListener {
            applyViewModel.applyCancel(apply.seq.toString())
        }

        // 신청 취소 성공
        applyViewModel.applyCancelCheck.observe(viewLifecycleOwner, Observer {
            if (it) {
                applyViewModel.applyCancelCheck.postValue(false)
                Toast.makeText(context, "취소 성공", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        })

        // 대출 불가, 신청취소일때는 신청취소 버튼 숨기기
        if (apply.status == Code.APPLY_STATUS_NO || apply.status == Code.APPLY_STATUS_CANCEL){
            binding.dialogApplyDetailCancel.visibility=View.GONE
        }

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
