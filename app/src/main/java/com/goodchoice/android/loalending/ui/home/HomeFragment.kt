package com.goodchoice.android.loalending.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentHomeBinding
import com.goodchoice.android.loalending.ui.HeaderBackActivity
import com.goodchoice.android.loalending.ui.HeaderCloseActivity
import com.goodchoice.android.loalending.ui.MainActivity
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import com.goodchoice.android.loalending.util.CustomToast
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val loginViewModel: LoginViewModel by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeApplyMinute.setOnClickListener {
            loginViewModel.tempReturnPage = Code.TITLE_APPLY
            if(!loginViewModel.isLogin.value!!) {
                (requireActivity() as MainActivity).moveLoginActivity()
                return@setOnClickListener
            }
            val intent = Intent(binding.root.context, HeaderCloseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("title", Code.TITLE_APPLY)
            binding.root.context.startActivity(intent)
        }
        binding.homeApply.setOnClickListener {
            loginViewModel.tempReturnPage = Code.TITLE_APPLY
            if(!loginViewModel.isLogin.value!!) {
                (requireActivity() as MainActivity).moveLoginActivity()
                return@setOnClickListener
            }
            val intent = Intent(binding.root.context, HeaderCloseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("title", Code.TITLE_APPLY)
            binding.root.context.startActivity(intent)
        }
    }


}