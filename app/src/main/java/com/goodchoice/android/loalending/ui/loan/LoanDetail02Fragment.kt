package com.goodchoice.android.loalending.ui.loan

import android.os.Bundle
import android.view.View
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoanDetail02Binding
import com.goodchoice.android.loalending.util.comma
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoanDetail02Fragment :
    BaseFragment<FragmentLoanDetail02Binding>(R.layout.fragment_loan_detail_02) {

    private val loanViewModel: LoanViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loanSeq=requireActivity().intent.getIntExtra("loanSeq",0)
        for(i in loanViewModel.loanList){
            if(i.seq==loanSeq){
                binding.loan=i
                binding.loanDetail02Amount.text="${comma(i.loanAmount.toLong())} LKRW"
                break;
            }
        }
    }

}