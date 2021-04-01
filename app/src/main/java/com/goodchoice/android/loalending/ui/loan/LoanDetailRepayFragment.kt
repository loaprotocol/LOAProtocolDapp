package com.goodchoice.android.loalending.ui.loan

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoanDetailRepayBinding

class LoanDetailRepayFragment :
    BaseFragment<FragmentLoanDetailRepayBinding>(R.layout.fragment_loan_detail_repay) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loanDetailRepayRg.setOnCheckedChangeListener { group, checkedId ->
            //loa
            if (checkedId == binding.loanDetailRepayRbLoa.id) {
                binding.loanDetailRepayDiscount.visibility = View.VISIBLE
                binding.loanDetailRepayTotal.text = "0 LOA"
                binding.loanDetailRepayPrincipal.text = "0 LOA"
                binding.loanDetailRepayInterest.text = "0 LOA"
                binding.loanDetailRepayInterestDiscount.text = "0 LOA"
                binding.loanDetailRepayInterest.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.loanDetailRepayInterest.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorCGrey
                    )
                )

            }
            //lkrw
            else {
                binding.loanDetailRepayDiscount.visibility = View.GONE
                binding.loanDetailRepayTotal.text = "0 LKRW"
                binding.loanDetailRepayPrincipal.text = "0 LKRW"
                binding.loanDetailRepayInterest.text = "0 LKRW"
                binding.loanDetailRepayInterestDiscount.text = "0 LKRW"
                binding.loanDetailRepayInterest.paintFlags = 0
                binding.loanDetailRepayInterest.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorTextBlack
                    )
                )
            }
        }
    }
}