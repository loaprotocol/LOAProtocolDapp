package com.goodchoice.android.loalending.ui.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.DialogSecessionBinding
import com.goodchoice.android.loalending.ui.apply.ApplyViewModel
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.android.ext.android.inject
import timber.log.Timber

class SecessionDialog : DialogFragment() {

    lateinit var binding: DialogSecessionBinding
    private val loginViewModel: LoginViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_secession,
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 닫기버튼 클릭
        binding.secessionClose.setOnClickListener {
            dismiss()
        }

        // 탈퇴하기 버튼 클릭
        binding.secessionSubmit.setOnClickListener {
            loginViewModel.secession()
        }

        // 탈퇴하기 response
        loginViewModel.secessionCode.observe(viewLifecycleOwner, Observer {
            when(it){
                Code.SECESSION_SUCCESS ->{
                    Toast.makeText(context,"탈퇴 성공",Toast.LENGTH_SHORT).show()
                    loginViewModel.isLogin.postValue(false)
                    requireActivity().finish()
                }
                Code.SECESSION_FAIL ->{
                    Toast.makeText(context,"처리할 수 없는 명령입니다",Toast.LENGTH_SHORT).show()
                }
            }
            loginViewModel.secessionCode.postValue("")
        })
    }
}