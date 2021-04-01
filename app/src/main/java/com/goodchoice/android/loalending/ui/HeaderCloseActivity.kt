package com.goodchoice.android.loalending.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseActivity
import com.goodchoice.android.loalending.databinding.ActivityHeaderCloseBinding
import com.goodchoice.android.loalending.ui.apply.ApplyFragment
import com.goodchoice.android.loalending.util.constant.Code
import com.goodchoice.android.loalending.util.hideKeyboard
import timber.log.Timber

class HeaderCloseActivity :
    BaseActivity<ActivityHeaderCloseBinding>(R.layout.activity_header_close) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val title = intent.getStringExtra("title")
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //대출하기 일때
        if (title == Code.TITLE_APPLY) {
            fragmentTransaction.replace(binding.headerCloseMainFragment.id, ApplyFragment())
                .commitNow()
            binding.headerCloseHeader.headerCloseTitle.text="대출하기"
        }

        binding.headerCloseHeader.headerCloseX.setOnClickListener {
            finish()
        }




    }
}