package com.goodchoice.android.loalending.ui.loan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.ItemLoanListBinding
import com.goodchoice.android.loalending.model.Loan
import com.goodchoice.android.loalending.ui.HeaderBackActivity
import com.goodchoice.android.loalending.util.constant.Code
import com.goodchoice.android.loalending.util.monthCal
import timber.log.Timber

class LoanListAdapter() :
    ListAdapter<Loan, LoanListAdapter.LoanListViewHolder>(LoanListDiffUtil) {

    inner class LoanListViewHolder(private val binding: ItemLoanListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Loan) {
            binding.loan = item
            item.loanTermMonth = monthCal(item.loanTermSdate, item.loanTermEdate).toString()
            // 추후 수정
            item.statusName = "상환중"
            Timber.e(item.collateralAddr1)
            if (item.status == Code.LOAN_STATUS_END) {
                binding.itemLoanListRepay.visibility = View.GONE
            } else {
                binding.itemLoanListRepay.visibility = View.VISIBLE
            }
            binding.itemLoanListDetail.setOnClickListener {
                val intent = Intent(it.context, HeaderBackActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("title", Code.TITLE_LOAN_DETAIL)
                intent.putExtra("loanSeq", item.seq)
                intent.putExtra("applySeq", item.apply_seq)
                it.context.startActivity(intent)
            }
            binding.itemLoanListRepay.setOnClickListener {
                val intent = Intent(it.context, HeaderBackActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("title", Code.TITLE_LOAN_DETAIL_REPAY)
                intent.putExtra("loanSeq", item.seq)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<ItemLoanListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_loan_list,
            parent,
            false
        ).let {
            LoanListViewHolder(it)
        }

    override fun onBindViewHolder(holder: LoanListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object LoanListDiffUtil : DiffUtil.ItemCallback<Loan>() {
    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem.seq == newItem.seq
    }

    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem == newItem
    }

}