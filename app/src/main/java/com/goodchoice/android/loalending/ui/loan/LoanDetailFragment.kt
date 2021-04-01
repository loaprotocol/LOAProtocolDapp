package com.goodchoice.android.loalending.ui.loan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoanDetailBinding
import com.google.android.material.tabs.TabLayout

class LoanDetailFragment : BaseFragment<FragmentLoanDetailBinding>(R.layout.fragment_loan_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(LoanDetail01Fragment())
        fragmentList.add(LoanDetail02Fragment())
        fragmentList.add(LoanDetail03Fragment())

        var childFt = childFragmentManager.beginTransaction()

        // 초기값
        childFt.replace(binding.loanDetailContents.id, fragmentList[0])
        childFt.commit()

        // 탭 생성
        val tabs = binding.loanDetailTab
        tabs.addTab(tabs.newTab().setText("상환정보"))
        tabs.addTab(tabs.newTab().setText("대출정보"))
        tabs.addTab(tabs.newTab().setText("대출신청정보"))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                childFt = childFragmentManager.beginTransaction()
                childFt.replace(binding.loanDetailContents.id, fragmentList[tab.position])
                childFt.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}



