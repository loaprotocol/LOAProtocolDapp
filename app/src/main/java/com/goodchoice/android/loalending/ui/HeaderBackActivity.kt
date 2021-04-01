package com.goodchoice.android.loalending.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseActivity
import com.goodchoice.android.loalending.databinding.ActivityHeaderBackBinding
import com.goodchoice.android.loalending.ui.loan.LoanDetailFragment
import com.goodchoice.android.loalending.ui.loan.LoanDetailRepayFragment
import com.goodchoice.android.loalending.ui.login.LoginFragment
import com.goodchoice.android.loalending.ui.more.MoreCompanyInfoFragment
import com.goodchoice.android.loalending.ui.more.MorePrivacyPolicyFragment
import com.goodchoice.android.loalending.ui.more.MoreTermFragment
import com.goodchoice.android.loalending.ui.more.MypageFragment
import com.goodchoice.android.loalending.ui.myAsset.*
import com.goodchoice.android.loalending.util.constant.Code

class HeaderBackActivity :
    BaseActivity<ActivityHeaderBackBinding>(R.layout.activity_header_back) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        when (intent.getStringExtra("title")) {
            //회사소개
            Code.TITLE_COMPANY_INFO -> {
                mainFragmentReplace(MoreCompanyInfoFragment(), "회사소개")
            }
            //이용방침
            Code.TITLE_TERM -> {
                mainFragmentReplace(MoreTermFragment(), "이용약관")
            }
            //개인정보 처리방침
            Code.TITLE_PRIVACY_POLICY ->{
                mainFragmentReplace(MorePrivacyPolicyFragment(),"개인정보 처리방침")
            }
            //로그인
            Code.TITLE_LOGIN -> {
                mainFragmentReplace(LoginFragment(), "로그인")
            }
            //마이 페이지
            Code.TITLE_MYPAGE -> {
                mainFragmentReplace(MypageFragment(), "마이페이지")
            }
            // 원화입금
            Code.TITLE_DEPOSIT_KRW -> {
                mainFragmentReplace(MyAssetDepositKrwFragment(), "원화 입금하기")
            }
            // 원화출금
            Code.TITLE_WITHDRAW_KRW -> {
                mainFragmentReplace(MyAssetWithdrawKrwFragment(), "원화 출금하기")
            }
            // LOA 입금
            Code.TITLE_DEPOSIT_LOA -> {
                mainFragmentReplace(MyAssetDepositLoaFragment(), "LOA 입금하기")
            }
            //LOA 출금
            Code.TITLE_WITHDRAW_LOA -> {
                mainFragmentReplace(MyAssetWithdrawLoaFragment(), "LOA 출금하기")
            }
            // 환전
            Code.TITLE_EXCHANGE -> {
                mainFragmentReplace(MyAssetExchange(), "환전하기")
            }
            // 상환
            Code.TITLE_MY_ASSET_REPAY -> {
                mainFragmentReplace(MyAssetRepayFragment(), "상환하기")
            }
            // 대출상세
            Code.TITLE_LOAN_DETAIL -> {
                mainFragmentReplace(LoanDetailFragment(), "대출상세")
            }
            // 상환하기(loan)
            Code.TITLE_LOAN_DETAIL_REPAY ->{
                mainFragmentReplace(LoanDetailRepayFragment(),"상환하기")
            }

        }


        binding.headerBackHeader.headerBackTitle.setOnClickListener {
            finish()
        }
        binding.headerBackHeader.headerBackIcon.setOnClickListener {
            finish()
        }


    }

    private fun mainFragmentReplace(fragment: Fragment, title: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            binding.headerBackMainFragment.id,
            fragment
        ).commitNow()
        binding.headerBackHeader.headerBackTitle.text = title
    }

}