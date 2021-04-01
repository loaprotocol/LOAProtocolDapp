package com.goodchoice.android.loalending.ui.loan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.base.BaseFragment
import com.goodchoice.android.loalending.databinding.FragmentApplyListBinding
import com.goodchoice.android.loalending.ui.apply.ApplyViewModel
import com.goodchoice.android.loalending.ui.login.LoginViewModel
import com.goodchoice.android.loalending.util.constant.Code
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ApplyListFragment : BaseFragment<FragmentApplyListBinding>(R.layout.fragment_apply_list) {


    private val loanViewModel: LoanViewModel by viewModel()
    private val applyViewModel: ApplyViewModel by inject()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = loanViewModel

            loanViewModel.getApplyList()
            // adapter 연결
            applyListRv.adapter = ApplyListAdapter(this@ApplyListFragment)


            //applyList change catch
            loanViewModel.applyListChange.observe(viewLifecycleOwner, Observer {
                if (it) {
                    loanViewModel.applyListChange.postValue(false)
                    // applyList data 변경
                    (applyListRv.adapter as ApplyListAdapter).submitList(loanViewModel.applyList)
                    if (loanViewModel.applyList.isEmpty()) {
                        binding.applyListRv.visibility = View.GONE
                        binding.applyListNone.visibility = View.VISIBLE
                    } else {
                        binding.applyListRv.visibility = View.VISIBLE
                        binding.applyListNone.visibility = View.GONE
                    }
                    // 대출신청 갯수
                    applyListCnt.text = "대출신청 ${loanViewModel.applyList.size} 건"
                }
            })

            // 취소했을때 상태를 변경해줌
            applyViewModel.applyCancelCheck.observe(viewLifecycleOwner, {
                if (it) {
                    val applyList = (binding.applyListRv.adapter as ApplyListAdapter).currentList
                    for (i in applyList.indices) {
                        if (applyViewModel.lastApply.seq == applyList[i].seq) {
//                            (binding.applyListRv.adapter as ApplyListAdapter).currentList[i].status =
//                                Code.APPLY_STATUS_CANCEL
                            applyList[i].status = Code.APPLY_STATUS_CANCEL
                            applyList[i].statusName = "신청취소"

                            (binding.applyListRv.adapter as ApplyListAdapter).notifyItemChanged(i)
                        }
                    }
                }
            })

        }
    }
}