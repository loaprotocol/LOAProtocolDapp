package com.goodchoice.android.loalending.ui.myAsset

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentMyAssetBinding
import com.google.android.material.tabs.TabLayout

class MyAssetFragmentFragment : BaseFragment<FragmentMyAssetBinding>(R.layout.fragment_my_asset) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabs = binding.myAssetTab
        tabs.addTab(tabs.newTab().setText("원화"))
        tabs.addTab(tabs.newTab().setText("LOA"))
        tabs.addTab(tabs.newTab().setText("LKRW"))

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(MyAssetKrwFragment())
        fragmentList.add(MyAssetLoaFragment())
        fragmentList.add(MyAssetLkrwFragment())

        // childFragment
        var childFt = childFragmentManager.beginTransaction()
        // 초기값
        childFt.replace(binding.myAssetContents.id, fragmentList[0])
        childFt.commitNow()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                childFt = childFragmentManager.beginTransaction()
                childFt.replace(binding.myAssetContents.id, fragmentList[tab.position]).commitNow()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
}