package com.goodchoice.android.loalending.ui.loan

import android.os.Bundle
import android.view.View
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoanDetail03Binding
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.util.comma
import org.koin.android.ext.android.inject

class LoanDetail03Fragment :
    BaseFragment<FragmentLoanDetail03Binding>(R.layout.fragment_loan_detail_03) {

    private val loanViewModel: LoanViewModel by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val applySeq = requireActivity().intent.getIntExtra("applySeq", 0)
        for (i in loanViewModel.applyList) {
            if (i.seq == applySeq) {
                val apply = i.copy()
                apply.hopeLoan = comma(apply.hopeLoan.toLong())
                if (apply.maxLoan.isNullOrEmpty()) {
                    apply.maxLoan = "0"
                }
                apply.maxLoan = comma(apply.maxLoan!!.toLong())
                binding.apply = apply
                break;
            }
        }

    }
}