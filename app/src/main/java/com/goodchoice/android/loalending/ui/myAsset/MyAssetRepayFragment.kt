package com.goodchoice.android.loalending.ui.myAsset

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentMyAssetRepayBinding

class MyAssetRepayFragment :
    BaseFragment<FragmentMyAssetRepayBinding>(R.layout.fragment_my_asset_repay) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 상환할 대출 선택
        binding.myAssetRepaySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

}