package com.goodchoice.android.loalending.ui.loan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.ItemLoanDetailRepayBinding
import com.goodchoice.android.loalending.model.Loan

class LoanDetailRepayAdapter :
    ListAdapter<Loan, LoanDetailRepayAdapter.LoanDetailRepayViewHolder>(LoanDetailRepayDiffUtil) {

    inner class LoanDetailRepayViewHolder(private val binding: ItemLoanDetailRepayBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        fun bind(item: Loan) {
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanDetailRepayViewHolder =
        DataBindingUtil.inflate<ItemLoanDetailRepayBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_loan_detail_repay,
            parent,
            false
        ).let {
            LoanDetailRepayViewHolder(it)
        }

    override fun onBindViewHolder(holder: LoanDetailRepayViewHolder, position: Int) {
//        holder.bind(getItem(position))
    }

}

object LoanDetailRepayDiffUtil : DiffUtil.ItemCallback<Loan>() {
    override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem.seq == newItem.seq
    }

    override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
        return oldItem == newItem
    }

}