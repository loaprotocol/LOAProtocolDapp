package com.goodchoice.android.loalending.ui.myAsset

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentMyAssetLkrwBinding
import com.goodchoice.android.loalending.ui.HeaderBackActivity
import com.goodchoice.android.loalending.util.constant.Code

class MyAssetLkrwFragment :
    BaseFragment<FragmentMyAssetLkrwBinding>(R.layout.fragment_my_asset_lkrw) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 환전
        binding.myAssetLkrwExchange.setOnClickListener {
            val intent = Intent(requireContext(), HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_EXCHANGE)
            requireContext().startActivity(intent)
        }
        // 상환
        binding.myAssetLkrwRepay.setOnClickListener {
            val intent = Intent(requireContext(), HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_MY_ASSET_REPAY)
            requireContext().startActivity(intent)
        }
    }
}