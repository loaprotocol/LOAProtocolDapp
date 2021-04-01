package com.goodchoice.android.loalending.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseActivity
import com.goodchoice.android.loalending.databinding.ActivityMainBinding
import com.goodchoice.android.loalending.ui.home.HomeFragment
import com.goodchoice.android.loalending.ui.loan.LoanFragment
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import com.goodchoice.android.loalending.ui.more.MoreFragment
import com.goodchoice.android.loalending.ui.myAsset.MyAssetFragmentFragment
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val host: NavHostFragment =
//            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
//        binding.mainBottomNav.setupWithNavController(host.navController)


        binding.mainBottomNav.setOnNavigationItemSelectedListener { item ->
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.homeFragment -> {
                    fragmentTransaction.replace(binding.mainFragment.id, HomeFragment()).commitNow()
                    true
                }
                R.id.loanFragment -> {
                    loginViewModel.tempReturnPage = Code.TITLE_LOAN
                    if (!loginViewModel.isLogin.value!!) {
                        moveLoginActivity()
                        return@setOnNavigationItemSelectedListener false
                    }
                    fragmentTransaction.replace(
                        binding.mainFragment.id,
                        LoanFragment()
                    ).commitNow()
                    true
                }
                // 대출하기는 activity호출
                R.id.applyActivity -> {
                    loginViewModel.tempReturnPage = Code.TITLE_APPLY
                    if (!loginViewModel.isLogin.value!!) {
                        moveLoginActivity()
                        return@setOnNavigationItemSelectedListener false
                    }
                    val intent = Intent(binding.root.context, HeaderCloseActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("title", Code.TITLE_APPLY)
                    binding.root.context.startActivity(intent)
                    false
                }
                R.id.myAssetFragment -> {
                    loginViewModel.tempReturnPage = Code.TITLE_MY_ASSET
                    if (!loginViewModel.isLogin.value!!) {
                        moveLoginActivity()
                        return@setOnNavigationItemSelectedListener false
                    }
                    fragmentTransaction.replace(
                        binding.mainFragment.id,
                        MyAssetFragmentFragment()
                    ).commitNow()
                    true
                }
                R.id.moreFragment -> {
                    fragmentTransaction.replace(
                        binding.mainFragment.id,
                        MoreFragment()
                    ).commitNow()
                    true
                }
                else -> false

            }
        }

        // 로그인 후 클릭한 페이지로 이동
        loginViewModel.returnPage.observe(this, {
            when (it) {
                Code.TITLE_LOAN -> {
                    loginViewModel.returnPage.postValue("")
                    binding.mainBottomNav.selectedItemId = R.id.loanFragment
                }
                Code.TITLE_MY_ASSET -> {
                    loginViewModel.returnPage.postValue("")
                    binding.mainBottomNav.selectedItemId = R.id.myAssetFragment
                }
            }
        })

    }

    fun moveLoginActivity() {
        val intent = Intent(baseContext, HeaderBackActivity::class.java)
        intent.putExtra("title", Code.TITLE_LOGIN)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        baseContext.startActivity(intent)
    }


}