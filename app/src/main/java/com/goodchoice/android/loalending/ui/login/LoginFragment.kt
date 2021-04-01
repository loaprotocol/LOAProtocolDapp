package com.goodchoice.android.loalending.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoginBinding
import com.goodchoice.android.loalending.ui.HeaderBackActivity
import com.goodchoice.android.loalending.ui.HeaderCloseActivity
import com.goodchoice.android.loalending.ui.WebViewActivity
import com.goodchoice.android.loalending.util.CustomToast
import com.goodchoice.android.loalending.util.constant.BaseUrl
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.android.ext.android.inject
import timber.log.Timber


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {


    private val loginViewModel: LoginViewModel by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //로그인 버튼 클릭
        binding.loginSubmit.setOnClickListener {
            if (binding.loginEmail.text.isNullOrEmpty()) {
                CustomToast(requireContext(), "이메일을 입력해 주세요").warningToast().show()
                return@setOnClickListener
            }
            if (binding.loginPw.text.isNullOrEmpty()) {
                CustomToast(requireContext(), "비밀번호를 입력해 주세요").warningToast().show()
                return@setOnClickListener
            }

            loginViewModel.login(
                binding.loginEmail.text.toString(),
                binding.loginPw.text.toString()
            )
        }
        loginViewModel.loginCode.observe(viewLifecycleOwner, {

            if (it == Code.LOGIN_SUCCESS) {
                loginViewModel.isLogin.postValue(true)
                loginViewModel.loginCode.postValue("")

                // 액티비티 경우에는 약간 딜레이가 생기기 때문에 여기서 바로 실행
                if (loginViewModel.tempReturnPage == Code.TITLE_APPLY) {
                    val intent = Intent(binding.root.context, HeaderCloseActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("title", Code.TITLE_APPLY)
                    binding.root.context.startActivity(intent)
                } else {
                    loginViewModel.returnPage.postValue(loginViewModel.tempReturnPage)
                }
                loginViewModel.tempReturnPage = ""
                requireActivity().finish()

            } else if (it == Code.LOGIN_FAIL) {
                CustomToast(requireContext(), "아이디 또는 비밀번호를 확인해주세요.").warningToast().show()
                loginViewModel.loginCode.postValue("")
            }
        })

        // 회원가입 클릭
        binding.loginSignUp.setOnClickListener {
            val intent = Intent(binding.root.context, WebViewActivity::class.java)
            intent.putExtra("title", Code.TITLE_SIGN_UP)
            intent.putExtra("url", BaseUrl.SIGN_UP)
            binding.root.context.startActivity(intent)

        }

    }
}