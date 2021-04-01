package com.goodchoice.android.loalending.ui.more

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentMypageBinding
import com.goodchoice.android.loalending.model.MemberInfo
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import com.goodchoice.android.loalending.util.constant.Code
import com.goodchoice.android.loalending.util.phoneInsertHyphen
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.regex.Pattern

class MypageFragment
    : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val loginViewModel: LoginViewModel by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        val memberInfo = MemberInfo(
            loginViewModel.memberInfo!!.name,
            loginViewModel.memberInfo!!.email,
            phoneInsertHyphen(loginViewModel.memberInfo!!.phone)
        )
        binding.memberInfo = memberInfo

        // 비밀번호 변경 클릭
        binding.mypagePwChange.setOnClickListener {
            // 새비밀번호 형식체크
            val pattern = Pattern.compile("^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}\$")
            if (!pattern.matcher(binding.mypagePwdNew.text.toString()).matches()) {
                loginViewModel.pwChangeCode.postValue(Code.PWD_CHANGE_TYPE)
                return@setOnClickListener
            }
            if (binding.mypagePwdNew.text.toString() != binding.mypagePwdRe.text.toString()) {
                loginViewModel.pwChangeCode.postValue(Code.PWD_CHANGE_RE)
                return@setOnClickListener
            }
            loginViewModel.pwChange(
                binding.mypagePwdOld.text.toString(),
                binding.mypagePwdNew.text.toString(),
                binding.mypagePwdRe.text.toString()
            )
        }
        // 비밀번호 변경 이벤트 확인
        loginViewModel.pwChangeCode.observe(viewLifecycleOwner, {
            when (it) {
                // 성공
                Code.PWD_CHANGE_SUCCESS -> {
                    Toast.makeText(context, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                    binding.mypagePwdReX.visibility = View.GONE
                    binding.mypagePwdOldX.visibility = View.GONE
                    binding.mypagePwdTypeX.visibility = View.GONE

                    binding.mypagePwdOld.text.clear()
                    binding.mypagePwdNew.text.clear()
                    binding.mypagePwdRe.text.clear()
                }
                // 현재비밀번호가 일치하지 않을때
                Code.PWD_CHANGE_OLD_X -> {
                    binding.mypagePwdReX.visibility = View.GONE
                    binding.mypagePwdOldX.visibility = View.VISIBLE
                    binding.mypagePwdTypeX.visibility = View.GONE
                }
                // 로그아웃 됐을때
                Code.PWD_CHANGE_LOGOUT -> {
                    Toast.makeText(context, "로그아웃 되었습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
                    loginViewModel.isLogin.postValue(false)
                    requireActivity().finish()
                }
                Code.PWD_CHANGE_TYPE -> {
                    //비밀번호 형식이 일치하지 않을때
                    binding.mypagePwdReX.visibility = View.GONE
                    binding.mypagePwdOldX.visibility = View.GONE
                    binding.mypagePwdTypeX.visibility = View.VISIBLE
                }
                Code.PWD_CHANGE_RE -> {
                    // 새비밀번호 2개가 일치하지않을때
                    binding.mypagePwdReX.visibility = View.VISIBLE
                    binding.mypagePwdOldX.visibility = View.GONE
                    binding.mypagePwdTypeX.visibility = View.GONE
                }
            }
            // 초기화
            loginViewModel.pwChangeCode.postValue("")
        })

        //회원탈퇴 클릭
        binding.mypageMemberSecession.setOnClickListener {
            val dialog=SecessionDialog()
            val fragmentTransaction=this.childFragmentManager.beginTransaction()
            dialog.show(fragmentTransaction,"")
        }

        //로그아웃 클릭
        binding.mypageMemberLogout.setOnClickListener {
            loginViewModel.logout()
        }

        // 로그아웃 이벤트 확인
        loginViewModel.logoutCode.observe(viewLifecycleOwner, {
            when (it) {
                Code.LOGOUT_SUCCESS -> {
                    Toast.makeText(requireContext(), "정상적으로 처리 되었습니다.", Toast.LENGTH_SHORT).show()
                    loginViewModel.logoutCode.postValue("")
                    loginViewModel.isLogin.postValue(false)
                    requireActivity().finish()
                }
            }
        })

    }
}