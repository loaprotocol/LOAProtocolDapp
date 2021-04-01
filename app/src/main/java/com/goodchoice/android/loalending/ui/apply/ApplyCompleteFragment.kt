package com.goodchoice.android.loalending.ui.apply

import android.os.Bundle
import android.view.View
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentApplyCompleteBinding
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.ui.loan.ApplyListDetailDialog
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplyCompleteFragment :
    BaseFragment<FragmentApplyCompleteBinding>(R.layout.fragment_apply_complete) {

    private val applyViewModel: ApplyViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = applyViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val apply = applyViewModel.lastApply
        binding.applyCompleteDetail.setOnClickListener {
            val dialog = ApplyListDetailDialog(apply)
            val fragmentTransaction =
                this@ApplyCompleteFragment.childFragmentManager.beginTransaction()
            dialog.show(fragmentTransaction, "")
        }
    }
}