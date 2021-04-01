package com.goodchoice.android.loalending.ui.loan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentLoanListBinding
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoanListFragment : BaseFragment<FragmentLoanListBinding>(R.layout.fragment_loan_list) {
    private val loanViewModel: LoanViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = loanViewModel
            loanViewModel.getLoanList()
            loanViewModel.getApplyList()

            // 갯수
            binding.loanListCnt.text = "대출 ${loanViewModel.loanList.size}건"

            // adapter 연결
            var loanList = loanViewModel.loanList.toMutableList()
            loanListRv.adapter = LoanListAdapter().apply {
                submitList(loanList)
            }
            if (loanList.isEmpty()) {
                binding.loanListRv.visibility = View.GONE
                binding.loanListNone.visibility = View.VISIBLE
            } else {
                binding.loanListRv.visibility = View.VISIBLE
                binding.loanListNone.visibility = View.GONE
            }

            // 대출 현황 리스트 뷰에 세팅
            loanViewModel.loanListChange.observe(viewLifecycleOwner, {
                loanList = loanViewModel.loanList.toMutableList()
                if (it) {
                    loanViewModel.loanListChange.postValue(false)

                    val adapter = loanListRv.adapter as LoanListAdapter
                    adapter.submitList(loanViewModel.loanList)
                    if (loanList.isEmpty()) {
                        binding.loanListRv.visibility = View.GONE
                        binding.loanListNone.visibility = View.VISIBLE
                    } else {
                        binding.loanListRv.visibility = View.VISIBLE
                        binding.loanListNone.visibility = View.GONE
                    }
                    loanListCnt.text = "대출 ${loanViewModel.loanList.size} 건"
                }
            })


            // 상환완료 체크박스
            loanListCompleteCheck.setOnClickListener {
                loanList = loanViewModel.loanList.toMutableList()
                if (loanList.isEmpty()) {
                    binding.loanListRv.visibility = View.GONE
                    binding.loanListNone.visibility = View.VISIBLE
                } else {
                    binding.loanListRv.visibility = View.VISIBLE
                    binding.loanListNone.visibility = View.GONE
                }
                if ((it as CheckBox).isChecked) {
                    // 체크했을때 (상환완료된 대출 숨기기)
                    for (i in loanList.size - 1 downTo 0) {
                        if (loanList[i].status == Code.LOAN_STATUS_END) {
                            loanList.removeAt(i)
                            (loanListRv.adapter as LoanListAdapter).notifyItemRemoved(i)
                        }
                    }
                    (loanListRv.adapter as LoanListAdapter).submitList(loanList)
                    loanListRv.smoothScrollToPosition(0)
                } else {
                    // 상환완료된 대출 다시 보여주기
                    (loanListRv.adapter as LoanListAdapter).submitList(loanViewModel.loanList)
                    loanListRv.smoothScrollToPosition(0)
                }
            }

        }
    }
}