package com.goodchoice.android.loalending.ui.loan

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoanBinding
import com.goodchoice.android.loalending.util.CustomToast
import com.google.android.material.tabs.TabLayout

class LoanFragment : BaseFragment<FragmentLoanBinding>(R.layout.fragment_loan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 탭으로 전환될 fragment를 리스트화
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(LoanListFragment())
        fragmentList.add(ApplyListFragment())

        // childFragment
        var childFt = childFragmentManager.beginTransaction()
        // 초기값
        childFt.replace(binding.fragmentLoanContents.id, fragmentList[0])
        childFt.commit()


        // 탭 생성
        val tabs = binding.fragmentLoanTab
        tabs.addTab(tabs.newTab().setText("대출"))
        tabs.addTab(tabs.newTab().setText("대출신청"))


        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            // 탭 선택
            override fun onTabSelected(tab: TabLayout.Tab) {
                childFt = childFragmentManager.beginTransaction()
                childFt.replace(binding.fragmentLoanContents.id, fragmentList[tab.position])
                childFt.commit()
//                CustomToast.createToast(binding.root.context, "테스트용 토스트").show()



            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }

}