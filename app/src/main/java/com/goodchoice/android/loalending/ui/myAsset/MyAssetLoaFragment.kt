package com.goodchoice.android.loalending.ui.myAsset

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentMyAssetBasicBinding
import com.goodchoice.android.loalending.ui.HeaderBackActivity
import com.goodchoice.android.loalending.util.constant.Code

class MyAssetLoaFragment :
    BaseFragment<FragmentMyAssetBasicBinding>(R.layout.fragment_my_asset_basic) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 입금
        binding.myAssetDepositBasic.setOnClickListener {
            val intent = Intent(requireContext(), HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_DEPOSIT_LOA)
            requireContext().startActivity(intent)
        }
        // 출금
        binding.myAssetWithdrawBasic.setOnClickListener {
            val intent = Intent(requireContext(), HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_WITHDRAW_LOA)
            requireContext().startActivity(intent)
        }
        // 환전
        binding.myAssetExchangeBasic.setOnClickListener {
            val intent = Intent(requireContext(), HeaderBackActivity::class.java)
            intent.putExtra("title", Code.TITLE_EXCHANGE)
            requireContext().startActivity(intent)
        }
    }
}