package com.goodchoice.android.loalending.ui.more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.BuildConfig
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentMoreBinding
import com.goodchoice.android.loalending.ui.HeaderBackActivity
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.android.ext.android.inject
import timber.log.Timber

class MoreFragment : BaseFragment<FragmentMoreBinding>(R.layout.fragment_more) {
    private val loginViewModel: LoginViewModel by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 버전 넣어주기
        binding.moreVersion.text=BuildConfig.VERSION_NAME
        // 회사 소개 클릭
        binding.moreCompanyInfo.setOnClickListener {
            val intent = Intent(binding.root.context, HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_COMPANY_INFO)
            binding.root.context.startActivity(intent)
        }
        // 이용 방침 클릭
        binding.moreTerm.setOnClickListener {
            val intent = Intent(binding.root.context, HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_TERM)
            binding.root.context.startActivity(intent)
        }
        // 개인정보 처리방침 클릭
        binding.morePrivacyPolicy.setOnClickListener {
            val intent = Intent(binding.root.context, HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_PRIVACY_POLICY)
            binding.root.context.startActivity(intent)
        }
        binding.moreLogin.setOnClickListener {
            // 비로그인 상태일때
            if (!loginViewModel.isLogin.value!!) {
                val intent = Intent(binding.root.context, HeaderBackActivity::class.java)
                intent.putExtra("title", Code.TITLE_LOGIN)
                binding.root.context.startActivity(intent)
            }

            // 로그인 상태일때
            else {
                val intent = Intent(context, HeaderBackActivity::class.java)
                intent.putExtra("title", Code.TITLE_MYPAGE)
                requireContext().startActivity(intent)
            }
        }
        // 로그인 중인지 체크
        loginViewModel.isLogin.observe(viewLifecycleOwner, Observer {

            if (it) {
                // 로그인 중일때
                binding.moreLogin.text = loginViewModel.memberInfo?.name
                binding.moreEmail.text = loginViewModel.memberInfo?.email
            } else {
                // 로그인 안했거나 풀렸을때
                binding.moreLogin.text = "로그인"
                binding.moreEmail.text = "로그인 후 서비스를 이용하실 수 있습니다."

            }
        })

    }
}